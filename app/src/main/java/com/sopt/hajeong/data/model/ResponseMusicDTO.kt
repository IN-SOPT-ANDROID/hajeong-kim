package com.sopt.hajeong.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResponseMusicDTO(
    @SerialName("statusCode")
    val statusCode: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Music>
) {
    @Serializable
    data class Music(
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("title")
        val title: String,
        @SerialName("singer")
        val singer: String,
    )
}
