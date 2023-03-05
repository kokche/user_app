package com.gen.userdataapp.presentation.ui.userslist

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.gen.userdataapp.domain.gerusers.GetUsersUseCase
import com.gen.userdataapp.domain.models.UserData
import com.gen.userdataapp.domain.updateuserdata.UpdateUserUseCase
import com.gen.userdataapp.presentation.UserDataFactory.userData
import com.gen.userdataapp.presentation.UserDataFactory.userDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
internal class UsersViewModelTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)
    private val getUsersUseCase: GetUsersUseCase = mock()
    private val updateUserUseCase: UpdateUserUseCase = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `check diff transformation`() = runTest {
        val data = PagingData.from(userDataList).filter { userData -> userData.isFavorite }
        val differ = AsyncPagingDataDiffer(
            diffCallback = MyDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(data)

        advanceUntilIdle()
        assertEquals(
            listOf(userData.copy(id = 2, firstName = "name is 2", isFavorite = true)),
            differ.snapshot().items
        )
    }


    @Test
    fun updateFavoriteUser() = runTest {
        val viewModel = UsersViewModel(getUsersUseCase, updateUserUseCase)
        viewModel.updateFavoriteUser(userData)
        testScope.launch {
            verify(updateUserUseCase).invoke(userData)
        }
    }
}

class NoopListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}

class MyDiffCallback : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem == newItem
    }
}
