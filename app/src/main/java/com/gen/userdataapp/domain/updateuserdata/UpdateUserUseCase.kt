package com.gen.userdataapp.domain.updateuserdata

import com.gen.userdataapp.domain.models.UserData

interface UpdateUserUseCase {
    suspend operator fun invoke(userData: UserData)
}