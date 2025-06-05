package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDao: SleepDao) {

    // Get all sleep
    suspend fun getAllSleeps(): List<Sleep> {
        return sleepDao.getAllSleep()
            .first() // Collect the first emission of the Flow
            .map { Sleep.fromDto(it) } // Convert every DTO in Exercise
    }

}