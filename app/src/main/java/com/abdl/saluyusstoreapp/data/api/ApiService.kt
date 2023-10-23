package com.abdl.saluyusstoreapp.data.api

import com.abdl.saluyusstoreapp.data.model.request.UserRegisRequest
import com.abdl.saluyusstoreapp.data.model.request.UserUpdateRequest
import com.abdl.saluyusstoreapp.data.model.response.UserResponse
import com.abdl.saluyusstoreapp.ui.presentation.screen.user.UserViewModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/saluyustore-service/api/auth/login")
    suspend fun login(
        @Body body: Map<String, String>,
    ): UserResponse

    @POST("/saluyustore-service/api/users")
    suspend fun register(
        @Body body: UserRegisRequest,
    ): UserResponse

    @GET("/saluyustore-service/api/users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): UserResponse

    @PUT("/saluyustore-service/api/users/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: String,
        @Body body: UserUpdateRequest
    ): UserResponse

    @POST("/saluyustore-service/api/auth/logout")
    suspend fun logout(): Response<Unit>
}