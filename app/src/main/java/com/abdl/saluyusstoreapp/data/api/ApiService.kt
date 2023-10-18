package com.abdl.saluyusstoreapp.data.api

import com.abdl.saluyusstoreapp.data.model.request.UserRegisRequest
import com.abdl.saluyusstoreapp.data.model.response.UserLoginResponse
import com.abdl.saluyusstoreapp.data.model.response.UserRegisResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/saluyustore-service/api/auth/login")
    suspend fun login(
        @Body body: Map<String, String>,
    ): UserRegisResponse

    @POST("/saluyustore-service/api/users")
    suspend fun register(
        @Body body: UserRegisRequest,
    ): UserRegisResponse
}