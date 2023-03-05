package com.gen.userdataapp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gen.userdataapp.DataFactory.userDataList
import com.gen.userdataapp.data.local.UserDatabase
import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.data.mapper.UserResponseToUserEntityMapper
import com.gen.userdataapp.data.remote.api.UserApi
import com.gen.userdataapp.data.remote.model.GetUsersResponse
import com.gen.userdataapp.data.repository.UserRepositoryRemoteMediator
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RemoteMediatorTest {

    private var mockApi = FakeUserApi()
    private val userResponseToUserEntityMapper = UserResponseToUserEntityMapper()

    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        UserDatabase::class.java
    ).build()

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }


    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val remoteMediator = UserRepositoryRemoteMediator(
            mockDb,
            mockApi,
            userResponseToUserEntityMapper
        )
        val pagingState = PagingState<Int, UserDataEntity>(
            listOf(),
            null,
            PagingConfig(12),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        // To test endOfPaginationReached, don't set up the mockApi to return post
        // data here.
        val remoteMediator = UserRepositoryRemoteMediator(
            mockDb,
            mockApi.apply {
                loadType = ApiLoadType.End
            },
            userResponseToUserEntityMapper
        )
        val pagingState = PagingState<Int, UserDataEntity>(
            listOf(),
            null,
            PagingConfig(12),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        // Set up failure message to throw exception from the mock API.
        val remoteMediator = UserRepositoryRemoteMediator(
            mockDb,
            mockApi.apply {
                loadType = ApiLoadType.Error
            },
            userResponseToUserEntityMapper
        )
        val pagingState = PagingState<Int, UserDataEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }
}

class FakeUserApi : UserApi {
    var loadType = ApiLoadType.Start
    override suspend fun getUsers(page: Int, perPage: Int): GetUsersResponse {
        return when (loadType) {
            ApiLoadType.Start -> {
                GetUsersResponse(
                    userDataList,
                    1,
                    12,
                    12,
                    1
                )
            }
            ApiLoadType.End -> {
                GetUsersResponse(
                    listOf(),
                    1,
                    12,
                    12,
                    1
                )
            }
            ApiLoadType.Error -> {
                throw IllegalAccessError("custom error")
            }
        }
    }
}

enum class ApiLoadType {
    Error,
    End,
    Start
}