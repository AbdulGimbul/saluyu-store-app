package com.abdl.saluyusstoreapp.data.model.response

import com.google.gson.annotations.SerializedName

data class UserRegisResponse(

	@field:SerializedName("data")
	val data: DataUserRegis? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataUserRegis(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("modifiedAt")
	val modifiedAt: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("profilePicture")
	val profilePicture: Any? = null,

	@field:SerializedName("userActive")
	val userActive: Boolean? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("createdBy")
	val createdBy: String? = null,

	@field:SerializedName("modifiedBy")
	val modifiedBy: String? = null,

	@field:SerializedName("userRole")
	val userRole: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("userToken")
	val userToken: String? = null,
)
