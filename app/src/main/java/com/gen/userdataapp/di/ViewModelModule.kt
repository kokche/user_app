package com.gen.userdataapp.di

import com.gen.userdataapp.presentation.ui.userinfo.UserDataViewModel
import com.gen.userdataapp.presentation.ui.userslist.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::UsersViewModel)
    viewModel { parametersHolder: ParametersHolder ->
        UserDataViewModel(
            get(),
            parametersHolder.get()
        )
    }
}