package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import java.time.LocalDateTime
import kotlin.test.assertFailsWith

class AddNewExerciseUseCaseTest {

    private lateinit var repository: ExerciseRepository
    private lateinit var useCase: AddNewExerciseUseCase

    @Before
    fun setUp() {
        repository = mock(ExerciseRepository::class.java)
        useCase = AddNewExerciseUseCase(repository)
    }

    @Test
    fun `when execute is called, repository addExercise should be called`() = runBlocking {
        // Arrange
        val newExercise = Exercise(
            id = 0,
            startTime = LocalDateTime.now(),
            duration = 45,
            intensity = ExerciseIntensity.Hard,
            type = ExerciseType.Running
        )

        // Act
        useCase.execute(newExercise)

        // Assert
        verify(repository).addExercise(newExercise)
    }

    @Test
    fun `when repository throws, use case should propagate the exception`() = runBlocking {
        // Arrange
        val exercise = Exercise(
            id = 1,
            startTime = LocalDateTime.now(),
            duration = 30,
            intensity = ExerciseIntensity.Low,
            type = ExerciseType.Riding
        )

        `when`(repository.addExercise(exercise)).thenThrow(IllegalStateException("DB error"))

        // Act & Assert
        val exception = assertFailsWith<IllegalStateException> {
            useCase.execute(exercise)
        }

        assertEquals("DB error", exception.message)
    }
}
