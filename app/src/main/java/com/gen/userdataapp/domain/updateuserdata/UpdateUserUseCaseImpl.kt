package com.gen.userdataapp.domain.updateuserdata

import com.gen.userdataapp.domain.UserRepository
import com.gen.userdataapp.domain.models.UserData

class UpdateUserUseCaseImpl(private val userRepository: UserRepository) : UpdateUserUseCase {
    override suspend fun invoke(userData: UserData) {
        userRepository.updateUserData(userData)
    }
}