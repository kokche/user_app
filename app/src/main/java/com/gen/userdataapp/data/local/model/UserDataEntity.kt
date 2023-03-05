package com.gen.userdataapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userData", primaryKeys = ["id", "email"])
data class UserDataEntity(
    val id: Int,
    val avatar: String,
    val email: String,
    val isFavorite: Boolean,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
)