package com.gen.userdataapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gen.userdataapp.data.local.model.UserDataEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM `userData`")
    fun getAllUsers(): PagingSource<Int, UserDataEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<UserDataEntity>)

    @Query("DELETE FROM `userData`")
    suspend fun deleteAll()

    @Update
    suspend fun updateUser(user: UserDataEntity)

    @Query("SELECT * FROM `userData` WHERE id = :userId")
    suspend fun getUserData(userId: Int): UserDataEntity
}