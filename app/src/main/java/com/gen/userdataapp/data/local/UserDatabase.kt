package com.gen.userdataapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.data.remote.model.Data

@Database(entities = [UserDataEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}