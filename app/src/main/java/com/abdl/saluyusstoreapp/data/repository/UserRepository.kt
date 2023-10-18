package com.abdl.saluyusstoreapp.data.repository

import com.abdl.saluyusstoreapp.data.api.ApiConfig
import com.abdl.saluyusstoreapp.data.model.request.UserRegisRequest
import com.abdl.saluyusstoreapp.data.model.response.UserLoginResponse
import com.abdl.saluyusstoreapp.data.model.response.UserRegisResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
    private val apiService = ApiConfig.getApiService()

    suspend fun login(username: String, password: String): Flow<UserRegisResponse> = flow {
        val requestBody = mapOf(
            "username" to username,
            "password" to password
        )
        emit(apiService.login(requestBody))
    }

    suspend fun register(
        username: String,
        email: String,
        password: String,
    ): Flow<UserRegisResponse> = flow {
        val requestBody = UserRegisRequest(
            username = username,
            email = email,
            password = password,
        )
        emit(apiService.register(requestBody))
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository = instance ?: synchronized(this) {
            UserRepository().apply {
                instance = this
            }
        }
    }
}