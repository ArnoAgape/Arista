package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciseDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDtoDao {
    @Insert
    suspend fun insertExercise(exercise: ExerciseDto): Int


    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciseDto>>


    @Query("DELETE FROM exercise WHERE exercise_id = :id")
    suspend fun deleteExerciseById(id: Int)


}