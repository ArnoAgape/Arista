package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(
    val id: Long,
    var nickname: String,
    var email: String,
    var gender: String,
    var age: Int,
    var weight: Double,
    var height: Double
) {

    companion object {
        fun fromDto(dto: UserDto): User {
            return User(
                id = dto.id,
                nickname = dto.nickname,
                email = dto.email,
                gender = dto.gender,
                age = dto.age,
                weight = dto.weight,
                height = dto.height
            )
        }
    }
}