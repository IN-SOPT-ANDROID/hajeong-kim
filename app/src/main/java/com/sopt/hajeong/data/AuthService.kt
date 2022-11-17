package com.sopt.hajeong.data

import retrofit2.Call
import retrofit2.http.POST

interface LoginService {
    //로그인 API
    @POST("api/user/signin")
    fun login(request: RequestLoginDTO): Call<ResponseLoginDTO>
}

interface SignupService{
    //회원가입 API
    @POST("api/user/signup")
    fun signup(request: RequestSignupDTO): Call<ResponseSignupDTO>
}