package com.gen.userdataapp.data.mapper

import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.domain.models.UserData

class UserEntityToUserMapper : (UserDataEntity) -> UserData {
    override fun invoke(userDataEntity: UserDataEntity): UserData = UserData(
        id = userDataEntity.id,
        avatar = userDataEntity.avatar,
        email = userDataEntity.email,
        isFavorite = userDataEntity.isFavorite,
        firstName = userDataEntity.firstName,
        lastName = userDataEntity.lastName,
    )
}