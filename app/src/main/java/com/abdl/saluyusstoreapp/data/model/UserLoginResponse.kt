package com.abdl.saluyusstoreapp.data.model

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

	@field:SerializedName("userToken")
	val userToken: String? = null,

	@field:SerializedName("roleId")
	val roleId: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
