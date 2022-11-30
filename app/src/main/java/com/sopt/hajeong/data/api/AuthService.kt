package com.sopt.hajeong.data.api

import com.sopt.hajeong.data.model.RequestLoginDTO
import com.sopt.hajeong.data.model.RequestSignupDTO
import com.sopt.hajeong.data.model.ResponseLoginDTO
import com.sopt.hajeong.data.model.ResponseSignupDTO
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    //로그인 API
    @POST("api/user/signin")
    fun login(@Body request: RequestLoginDTO): Call<ResponseLoginDTO>
    //회원가입 API
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDTO): Call<ResponseSignupDTO>
    //사진 가져오는 API
    @Multipart //Json으로 통신하는 것이 아니므로 Multipart라는 것을 선언해야함
    @POST("api/user/{userId}/image")
    fun uploadProfileImage(
        @Path("userId") userId: Int,
        @Part("image") image: MultipartBody.Part
    ): Call<Unit>
}
