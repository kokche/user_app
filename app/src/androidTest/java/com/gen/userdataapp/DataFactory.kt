package com.gen.userdataapp

import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.data.remote.model.Data

object DataFactory {
    val data = Data(
        id = 0,
        avatar = "Aang",
        email = "email",
        firstName = "first name",
        lastName = "last name"
    )
    val userDataList = List(5) {
        data.copy(id = it, firstName = "name is $it")
    }
}