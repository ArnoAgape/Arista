package com.openclassrooms.arista.data

import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.model.User
import java.time.LocalDateTime

class FakeApiService {

    // Static data
    var user: User = User("John Doe", "johndoe@example.com")

    private val sleepData = listOf(
        Sleep(LocalDateTime.now().minusDays(1), 7, 8),
        Sleep(LocalDateTime.now().minusDays(2), 6, 5),
        Sleep(LocalDateTime.now().minusDays(3), 8, 9)
    )

    private val exerciseData = mutableListOf(
        Exercise(1, LocalDateTime.now().minusHours(5), 30, ExerciseType.Running, ExerciseIntensity.Low),
        Exercise(
            2,
            LocalDateTime.now().minusDays(1).minusHours(3),
            45,
            ExerciseType.Swimming,
            ExerciseIntensity.Medium
        ),
        Exercise(
            3,
            LocalDateTime.now().minusDays(2).minusHours(4),
            60,
            ExerciseType.Football,
            ExerciseIntensity.Hard
        )
    )

    // CRUD for Sleep
    fun getAllSleeps() = sleepData.toList()

    fun findSleepByStartTime(startTime: LocalDateTime) =
        sleepData.find { it.startTime == startTime }

    // CRUD for Exercise
    fun getAllExercises() = exerciseData.toList()

    fun addExercise(exercise: Exercise) {
        exerciseData.add(exercise)
    }

    fun deleteExercise(exercise: Exercise) {
        exerciseData.remove(exercise)
    }
}
