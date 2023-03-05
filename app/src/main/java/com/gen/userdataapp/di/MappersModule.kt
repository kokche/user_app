package com.gen.userdataapp.di

import com.gen.userdataapp.data.mapper.UserDataToUserEntityMapper
import com.gen.userdataapp.data.mapper.UserEntityToUserMapper
import com.gen.userdataapp.data.mapper.UserResponseToUserEntityMapper
import org.koin.dsl.module

var mappersModule = module {
    factory { UserResponseToUserEntityMapper() }
    factory { UserEntityToUserMapper() }
    factory { UserDataToUserEntityMapper() }
}