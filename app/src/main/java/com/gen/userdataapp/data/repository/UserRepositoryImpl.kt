package com.gen.userdataapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gen.userdataapp.data.local.UserDao
import com.gen.userdataapp.data.mapper.UserDataToUserEntityMapper
import com.gen.userdataapp.data.mapper.UserEntityToUserMapper
import com.gen.userdataapp.domain.UserRepository
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userRepositoryRemoteMediator: UserRepositoryRemoteMediator,
    private val userResponseToUserEntityMapper: UserEntityToUserMapper,
    private val userDataToUserEntityMapper: UserDataToUserEntityMapper
) : UserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(pageSize: Int): Flow<PagingData<UserData>> {
        return Pager(
            PagingConfig(pageSize, enablePlaceholders = false),
            initialKey = 1,
            remoteMediator = userRepositoryRemoteMediator,
            pagingSourceFactory = userDao::getAllUsers
        ).flow.map { data -> data.map(userResponseToUserEntityMapper::invoke) }
    }

    override suspend fun updateUserData(userData: UserData) {
        userDao.updateUser(user = userData.run(userDataToUserEntityMapper))
    }

    override suspend fun getUserData(userId: Int): UserData =
        userDao.getUserData(userId).run(userResponseToUserEntityMapper)

}