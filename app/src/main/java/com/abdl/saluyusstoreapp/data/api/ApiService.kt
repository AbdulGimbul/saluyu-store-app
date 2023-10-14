package com.abdl.saluyusstoreapp.data.api

import com.abdl.saluyusstoreapp.data.model.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/saluyustore-service/api/auth/login")
    suspend fun login(
        @Body body: Map<String, String>
    ): UserLoginResponse
}