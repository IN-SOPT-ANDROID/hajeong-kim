package com.sopt.hajeong.data.api

import com.sopt.hajeong.data.model.ResponseMusicDTO
import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    //music 가져오는 API
    @GET("music/list")
    fun getMusic(): Call<ResponseMusicDTO>
}