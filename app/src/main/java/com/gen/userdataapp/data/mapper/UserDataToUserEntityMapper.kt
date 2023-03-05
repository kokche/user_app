package com.gen.userdataapp.data.mapper

import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.domain.models.UserData

class UserDataToUserEntityMapper : (UserData) -> UserDataEntity {
    override fun invoke(userData: UserData): UserDataEntity = UserDataEntity(
        id = userData.id,
        avatar = userData.avatar,
        email = userData.email,
        isFavorite = userData.isFavorite,
        firstName = userData.firstName,
        lastName = userData.lastName,
    )
}