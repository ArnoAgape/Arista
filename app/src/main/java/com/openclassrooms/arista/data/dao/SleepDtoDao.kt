package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassrooms.arista.data.entity.SleepDto
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface SleepDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleep(sleep: SleepDto): Int


    @Query("SELECT * FROM sleeping")
    fun getAllSleep(): Flow<List<SleepDto>>


    @Query("DELETE FROM sleeping WHERE timestamp_sleeping = :id")
    suspend fun deleteSleepById(id: LocalDateTime)


}