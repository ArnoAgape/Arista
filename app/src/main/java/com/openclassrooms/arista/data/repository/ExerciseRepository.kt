package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.domain.model.Exercise
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    // Get all exercises
    suspend fun getAllExercises(): Result<List<Exercise>> {
        return withContext(Dispatchers.IO) {
            try {
                val list = exerciseDao.getAllExercises()
                    .map { Exercise.fromDto(it) }
                Result.success(list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Add a new exercise
    suspend fun addExercise(exercise: Exercise): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                exerciseDao.insertExercise(exercise.toDto())
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                exercise.id?.let {
                    exerciseDao.deleteExerciseById(it)
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}