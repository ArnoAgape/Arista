package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class SleepRepository(private val sleepDao: SleepDao) {

    // Get all sleep
    suspend fun getAllSleeps(): Result<List<Sleep>> {
        return withContext(Dispatchers.IO) {
            try {
                val list = sleepDao.getAllSleep()
                    .map { Sleep.fromDto(it) }
                Result.success(list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}