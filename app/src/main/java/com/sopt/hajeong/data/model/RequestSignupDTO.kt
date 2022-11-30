package com.sopt.hajeong.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String
)