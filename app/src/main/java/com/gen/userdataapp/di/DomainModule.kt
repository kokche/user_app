package com.gen.userdataapp.di

import com.gen.userdataapp.data.repository.UserRepositoryImpl
import com.gen.userdataapp.domain.UserRepository
import com.gen.userdataapp.domain.gerusers.GetUsersUseCase
import com.gen.userdataapp.domain.gerusers.GetUsersUseCaseImpl
import com.gen.userdataapp.domain.getuserdata.GetUserDataUseCase
import com.gen.userdataapp.domain.getuserdata.GetUserDataUseCaseImpl
import com.gen.userdataapp.domain.updateuserdata.UpdateUserUseCase
import com.gen.userdataapp.domain.updateuserdata.UpdateUserUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    single<UpdateUserUseCase> { UpdateUserUseCaseImpl(get()) }
    single<GetUserDataUseCase> { GetUserDataUseCaseImpl(get()) }
}