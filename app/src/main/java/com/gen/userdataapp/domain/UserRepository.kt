package com.gen.userdataapp.domain

import androidx.paging.PagingData
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(pageSize: Int): Flow<PagingData<UserData>>
    suspend fun updateUserData(userData: UserData)
    suspend fun getUserData(userId:Int):UserData
}