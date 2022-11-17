package com.sopt.hajeong.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDTO(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("reuslt")
    val result: User
) {
    @Serializable
    data class User(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImage")
        val profileImage: String?,
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("password")
        val password: String
    )
}
