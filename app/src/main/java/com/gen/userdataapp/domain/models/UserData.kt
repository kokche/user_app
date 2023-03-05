package com.gen.userdataapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val id: Int,
    val avatar: String,
    val email: String,
    val isFavorite: Boolean,
    val firstName: String,
    val lastName: String,
) : Parcelable