package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.SleepDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDtoDao {
    @Insert
    suspend fun insertSleep(sleep: SleepDto): Int


    @Query("SELECT * FROM sleeping")
    fun getAllSleep(): Flow<List<SleepDto>>


    @Query("DELETE FROM sleeping WHERE sleeping_id = :id")
    suspend fun deleteSleepById(id: Int)


}