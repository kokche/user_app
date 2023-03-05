package com.gen.userdataapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.gen.userdataapp.data.local.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(androidContext(), UserDatabase::class.java, "UserDatabase")
            .build()
    }
    single { get<UserDatabase>().userDao() }
}