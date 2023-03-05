package com.gen.userdataapp.data.remote.api

import com.gen.userdataapp.data.remote.model.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 12
    ): GetUsersResponse
}