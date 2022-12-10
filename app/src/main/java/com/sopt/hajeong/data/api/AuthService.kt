package com.sopt.hajeong.data.api

import com.sopt.hajeong.data.model.RequestLoginDTO
import com.sopt.hajeong.data.model.RequestSignupDTO
import com.sopt.hajeong.data.model.ResponseLoginDTO
import com.sopt.hajeong.data.model.ResponseSignupDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    //로그인 API
    @POST("api/user/signin")
    fun login(@Body request: RequestLoginDTO): Call<ResponseLoginDTO>
    //회원가입 API
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDTO): Call<ResponseSignupDTO>
}
