package com.gen.userdataapp.domain.getuserdata

import com.gen.userdataapp.domain.UserRepository
import com.gen.userdataapp.domain.models.UserData

class GetUserDataUseCaseImpl(private val userRepository: UserRepository) : GetUserDataUseCase {
    override suspend fun invoke(userId: Int): UserData = userRepository.getUserData(userId)
}