package com.gen.userdataapp.presentation

import com.gen.userdataapp.domain.models.UserData

object UserDataFactory {
    val userData = UserData(
        id = 0,
        avatar = "Aang",
        email = "email",
        isFavorite = false,
        firstName = "first name",
        lastName = "last name"
    )
    val userDataList = List(5) {
        userData.copy(id = it, firstName = "name is $it", isFavorite = (it == 2))
    }
}