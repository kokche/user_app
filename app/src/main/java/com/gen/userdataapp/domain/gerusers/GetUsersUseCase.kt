package com.gen.userdataapp.domain.gerusers

import androidx.paging.PagingData
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    fun getUsers(pageSize: Int): Flow<PagingData<UserData>>
}