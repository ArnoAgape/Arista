package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first

class UserRepository(private val userDao: UserDao) {

    // Get User
    suspend fun getUser(): User {
        return userDao.getUser()
            .first()
            .let { User.fromDto(it) }
    }
}
