package com.gen.userdataapp.presentation.ui.userslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.gen.userdataapp.domain.gerusers.GetUsersUseCase
import com.gen.userdataapp.domain.updateuserdata.UpdateUserUseCase
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val PAGE_SIZE = 12

class UsersViewModel(
    getUsersUseCase: GetUsersUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val users = getUsersUseCase
        .getUsers(PAGE_SIZE)


    private var onlyFavorite = false

    fun getUsers() = users.map { pagingData ->
        pagingData.filter { userData ->
            if (onlyFavorite) {
                !userData.isFavorite
            } else {
                true
            }
        }
    }.cachedIn(viewModelScope)

    fun updateOnlyFavorites(checked: Boolean) {
        onlyFavorite = checked
    }

    fun updateFavoriteUser(checked: UserData) {
        viewModelScope.launch {
            updateUserUseCase(checked)
        }
    }
}