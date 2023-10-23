package com.abdl.saluyusstoreapp.data.model.request

data class UserUpdateRequest(
    val fullName: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
)
