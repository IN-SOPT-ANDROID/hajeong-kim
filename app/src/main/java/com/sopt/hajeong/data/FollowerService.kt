package com.sopt.hajeong.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users?page=1")
    fun getFollowerList(): Call<ResponseGetFollowerListDTO>
}