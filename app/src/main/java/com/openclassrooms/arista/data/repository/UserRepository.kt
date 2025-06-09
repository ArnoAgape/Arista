package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class UserRepository(private val userDao: UserDao) {

    // Get User
    suspend fun getUser(): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                val list = userDao.getUser()
                    .let { User.fromDto(it) }
                Result.success(list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
