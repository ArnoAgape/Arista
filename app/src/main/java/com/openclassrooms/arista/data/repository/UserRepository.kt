package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first

class UserRepository(private val userDao: UserDao) {

    // Get User
    suspend fun getUser(): Result<User> {
        return try {
            val list = userDao.getUser()
                .first()
                .let { User.fromDto(it) }
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
