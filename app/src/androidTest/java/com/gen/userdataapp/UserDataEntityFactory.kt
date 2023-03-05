package com.gen.userdataapp

import com.gen.userdataapp.data.local.model.UserDataEntity

object UserDataEntityFactory {
    val userDataEntity = UserDataEntity(
        id = 0,
        avatar = "Aang",
        email = "email",
        isFavorite = true,
        firstName = "first name",
        lastName = "last name"
    )
    val userDataList = List(5) {
        userDataEntity.copy(id = it, firstName = "name is $it", isFavorite = (it == 2).not())
    }
}