package com.gen.userdataapp.di

import com.gen.userdataapp.data.repository.UserRepositoryImpl
import com.gen.userdataapp.data.repository.UserRepositoryRemoteMediator
import com.gen.userdataapp.domain.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    single<UserRepositoryRemoteMediator> { UserRepositoryRemoteMediator(get(), get(), get()) }
}