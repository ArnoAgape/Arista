package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(
    val id: Long? = null,
    var nickname: String,
    var email: String,
    var password: String,
    var gender: String,
    var age: Int,
    var weight: Double,
    var height: Double
) {

    fun toDto(): UserDto {
        return UserDto(
            id = id,
            nickname = nickname,
            email = email,
            password = password,
            gender = gender,
            age = age,
            weight = weight,
            height = height
        )
    }

    companion object {
        fun fromDto(dto: UserDto): User {
            return User(
                id = dto.id,
                nickname = dto.nickname,
                email = dto.email,
                password = dto.password,
                gender = dto.gender,
                age = dto.age,
                weight = dto.weight,
                height = dto.height
            )
        }
    }
}