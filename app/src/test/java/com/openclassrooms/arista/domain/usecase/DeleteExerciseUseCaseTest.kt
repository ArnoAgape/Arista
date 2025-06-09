package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import java.time.LocalDateTime
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class DeleteExerciseUseCaseTest {

    private lateinit var repository: ExerciseRepository
    private lateinit var useCase: DeleteExerciseUseCase

    @Before
    fun setUp() {
        repository = mock(ExerciseRepository::class.java)
        useCase = DeleteExerciseUseCase(repository)
    }

    @Test
    fun `when execute is called, repository deleteExercise should be called`() {
        runBlocking {
            // Arrange
            val exercise = Exercise(
                id = 0,
                startTime = LocalDateTime.now(),
                duration = 45,
                intensity = ExerciseIntensity.Hard,
                type = ExerciseType.Running
            )

            // Act
            useCase.execute(exercise)

            // Assert
            verify(repository).deleteExercise(exercise)
        }
    }

    @Test
    fun `when repository throws, use case should propagate the exception`() {
        runBlocking {
            // Arrange
            val exercise = Exercise(
                id = 1,
                startTime = LocalDateTime.now(),
                duration = 30,
                intensity = ExerciseIntensity.Low,
                type = ExerciseType.Riding
            )

            `when`(repository.deleteExercise(exercise)).thenThrow(IllegalStateException("DB error"))

            // Act & Assert
            val exception = assertFailsWith<IllegalStateException> {
                useCase.execute(exercise)
            }

            assertEquals("DB error", exception.message)
        }
    }
}
