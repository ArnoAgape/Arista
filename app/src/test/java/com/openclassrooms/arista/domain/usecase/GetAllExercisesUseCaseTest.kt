package com.openclassrooms.arista.domain.usecase


import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import java.time.LocalDateTime
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class GetAllExercisesUseCaseTest {

    private lateinit var repository: ExerciseRepository
    private lateinit var useCase: GetAllExercisesUseCase

    @Before
    fun setUp() {
        repository = mock(ExerciseRepository::class.java)
        useCase = GetAllExercisesUseCase(repository)
    }

    @Test
    fun `when repository returns exercises, use case should return them`() = runBlocking {
        val expected = listOf(
            Exercise(
                id = 1,
                startTime = LocalDateTime.now(),
                duration = 20,
                intensity = ExerciseIntensity.Medium,
                type = ExerciseType.Riding
            ),
            Exercise(
                id = 2,
                startTime = LocalDateTime.now().plusHours(1),
                duration = 20,
                intensity = ExerciseIntensity.Low,
                type = ExerciseType.Football
            )
        )
        `when`(repository.getAllExercises()).thenReturn(Result.success(expected))

        val result = useCase.execute()
        assertTrue(result.isSuccess)
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `when repository throws NoSuchElementException, use case should throw it`() = runBlocking {
        `when`(repository.getAllExercises()).thenThrow(NoSuchElementException("No list of exercises found"))

        val exception = assertFailsWith<NoSuchElementException> {
            useCase.execute()
        }

        assertEquals("No list of exercises found", exception.message)
    }
}
