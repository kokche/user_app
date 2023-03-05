package com.gen.userdataapp.data.mapper

import com.gen.userdataapp.data.local.model.UserDataEntity
import com.gen.userdataapp.data.remote.model.Data
import com.gen.userdataapp.data.remote.model.GetUsersResponse

class UserResponseToUserEntityMapper : (Data) -> UserDataEntity {
    override fun invoke(usersResponse: Data): UserDataEntity = UserDataEntity(
        id = usersResponse.id,
        avatar = usersResponse.avatar,
        email = usersResponse.email,
        isFavorite = false,
        firstName = usersResponse.firstName,
        lastName = usersResponse.lastName,
    )

}