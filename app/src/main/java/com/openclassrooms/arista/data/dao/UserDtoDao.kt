package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDtoDao {
    @Insert
    suspend fun insertUser(user: UserDto): Int


    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserDto>>


    @Query("DELETE FROM user WHERE user_id = :id")
    suspend fun deleteUserById(id: Int)


}