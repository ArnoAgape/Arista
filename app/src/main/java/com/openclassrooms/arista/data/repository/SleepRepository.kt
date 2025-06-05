package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDao: SleepDao) {

    // Get all sleep
    suspend fun getAllSleeps(): Result<List<Sleep>> {
        return try {
            val list = sleepDao.getAllSleep()
                .first() // Collect the first emission of the Flow
                .map { Sleep.fromDto(it) }
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}