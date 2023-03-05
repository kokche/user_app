package com.gen.userdataapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gen.userdataapp.data.local.UserDatabase
import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.data.mapper.UserResponseToUserEntityMapper
import com.gen.userdataapp.data.remote.api.UserApi
import com.gen.userdataapp.data.remote.model.GetUsersResponse

private const val PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class UserRepositoryRemoteMediator(
    private val userDb: UserDatabase,
    private val userApi: UserApi,
    private val userResponseToUserEntityMapper: UserResponseToUserEntityMapper
) : RemoteMediator<Int, UserDataEntity>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserDataEntity>
    ): MediatorResult {
        return runCatching {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                else -> state.pages.size
            }
            val data = userApi.getUsers(
                page = loadKey?.plus(PAGE) ?: PAGE,
                perPage = state.config.pageSize
            ).data

            userDb.withTransaction {
                userDb.userDao().insertAll(data.map(userResponseToUserEntityMapper::invoke))
            }
            data
        }.fold(onSuccess = { items ->
            MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        }, onFailure = {
            MediatorResult.Error(it)
        })
    }
}