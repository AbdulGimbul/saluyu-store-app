package com.abdl.saluyusstoreapp.data.repository

import com.abdl.saluyusstoreapp.data.api.ApiConfig
import com.abdl.saluyusstoreapp.data.api.ApiService
import com.abdl.saluyusstoreapp.data.model.request.UserRegisRequest
import com.abdl.saluyusstoreapp.data.model.request.UserUpdateRequest
import com.abdl.saluyusstoreapp.data.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(username: String, password: String): Flow<UserResponse> = flow {
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
    ): Flow<UserResponse> = flow {
        val requestBody = UserRegisRequest(
            username = username,
            email = email,
            password = password,
        )
        emit(apiService.register(requestBody))
    }

    suspend fun getUser(userId: String): Flow<UserResponse> = flow {
        emit(apiService.getUser(userId))
    }

    suspend fun update(
        userId: String,
        fullname: String,
        username: String,
        email: String,
        phoneNumber: String,
        address: String
    ): Flow<UserResponse> = flow {
        val requestBody = UserUpdateRequest(
            fullName = fullname,
            username = username,
            email = email,
            phoneNumber = phoneNumber,
            address = address
        )
        emit(apiService.updateUser(userId = userId, body = requestBody))
    }

    suspend fun logout(): Flow<Response<Unit>> = flow {
        emit(apiService.logout())
    }


//    companion object {
//        @Volatile
//        private var instance: UserRepository? = null
//
//        fun getInstance(): UserRepository = instance ?: synchronized(this) {
//            UserRepository().apply {
//                instance = this
//            }
//        }
//    }
}