package com.abdl.saluyusstoreapp.data.model.request

data class UserRegisRequest(
    val username: String,
    val email: String,
    val password: String,
    val userActive: Boolean = true,
)
