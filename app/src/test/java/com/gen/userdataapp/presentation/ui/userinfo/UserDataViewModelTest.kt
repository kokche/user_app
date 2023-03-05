package com.gen.userdataapp.presentation.ui.userinfo

import com.gen.userdataapp.domain.getuserdata.GetUserDataUseCase
import com.gen.userdataapp.presentation.UserDataFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class UserDataViewModelTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun getUserData() = runTest {
        val getUserDataUseCase: GetUserDataUseCase = mock()

        testScope.launch {
        whenever(getUserDataUseCase.invoke(10)) doReturn UserDataFactory.userData

            UserDataViewModel(getUserDataUseCase, 10)
            verify(getUserDataUseCase).invoke(10)
        }

    }
}