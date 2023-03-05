package com.gen.userdataapp.presentation.ui.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gen.userdataapp.domain.getuserdata.GetUserDataUseCase
import com.gen.userdataapp.domain.models.UserData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserDataViewModel(
    private val getUserDataUseCase: GetUserDataUseCase, userId: Int
) : ViewModel() {
    private val _userData = MutableSharedFlow<UserData?>(replay = 1)

    val userData: SharedFlow<UserData?>
        get() = _userData.asSharedFlow()

    init {
        viewModelScope.launch {
            _userData.emit(getUserDataUseCase(userId))
        }
    }
}