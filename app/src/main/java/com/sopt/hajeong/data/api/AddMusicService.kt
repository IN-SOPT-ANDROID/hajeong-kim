package com.sopt.hajeong.data.api

import com.sopt.hajeong.data.model.ResponseMusicDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface AddMusicService {
    //musicList 가져오는 API
    @Multipart //Json으로 통신하는 것이 아니므로 Multipart라는 것을 선언해야함
    @POST("music")
    fun postMusic(
        @Part image: MultipartBody.Part?,
        @PartMap data : HashMap<String, RequestBody>
    ): Call<ResponseMusicDTO>
}