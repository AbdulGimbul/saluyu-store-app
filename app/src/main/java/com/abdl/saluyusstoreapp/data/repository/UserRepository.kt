package com.abdl.saluyusstoreapp.data.repository

import com.abdl.saluyusstoreapp.data.api.ApiConfig
import com.abdl.saluyusstoreapp.data.model.UserLoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class UserRepository {
    private val apiService = ApiConfig.getApiService()

    suspend fun login(username: String, password: String): Flow<UserLoginResponse> = flow {
        val requestBody = mapOf(
            "username" to username,
            "password" to password
        )
        emit(apiService.login(requestBody))
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository =
            instance ?: synchronized(this) {
                UserRepository().apply {
                    instance = this
                }
            }
    }
}