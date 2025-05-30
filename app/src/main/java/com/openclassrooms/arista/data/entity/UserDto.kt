package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long? = 0,


    @ColumnInfo(name = "nickname")
    var nickname: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "age")
    var age: Int,

    @ColumnInfo(name = "weight")
    var weight: Double,

    @ColumnInfo(name = "height")
    var height: Double
)
