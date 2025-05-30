package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDtoDao) {


    // Get all users
    suspend fun getUser(): User {
            return User.fromDto(userDao.getUser().first())
    }


    // Add a new user
    suspend fun addUser(user: User) {
        userDao.insertUser(user.toDto())
    }


    // Delete a user
    suspend fun deleteUser(user: User) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        user.id?.let {
            userDao.deleteUserById(
                id = user.id,
            )
        }
    }
}