package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.utils.HashUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUsecase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(): User {
        return repository.getUser()
    }
}
