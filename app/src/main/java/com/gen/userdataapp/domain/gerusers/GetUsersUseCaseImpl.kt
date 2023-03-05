package com.gen.userdataapp.domain.gerusers

import androidx.paging.PagingData
import com.gen.userdataapp.domain.UserRepository
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.Flow

class GetUsersUseCaseImpl(private val userRepository: UserRepository) : GetUsersUseCase {
    override fun getUsers(pageSize: Int): Flow<PagingData<UserData>> = userRepository.getUsers(pageSize)
}