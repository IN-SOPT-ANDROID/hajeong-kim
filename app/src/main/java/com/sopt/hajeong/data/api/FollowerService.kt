package com.sopt.hajeong.data.api

import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    //follower API
    @GET("api/users")
    //page는 유동적으로 변할 수 있는 값이므로 Query를 이용해준다
    fun getFollowerList(@Query("page") page: Int): Call<ResponseGetFollowerListDTO>
}