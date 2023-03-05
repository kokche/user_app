package com.gen.userdataapp.domain.getuserdata

import com.gen.userdataapp.domain.models.UserData

interface GetUserDataUseCase {
    suspend operator fun invoke(userId: Int): UserData
}