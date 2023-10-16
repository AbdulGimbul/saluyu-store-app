package com.abdl.saluyusstoreapp.data.model.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

	@field:SerializedName("data")
	val data: DataUserLogin? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,
)

data class DataUserLogin(

	@field:SerializedName("userToken")
	val userToken: String? = null,

	@field:SerializedName("roleId")
	val roleId: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,
)
