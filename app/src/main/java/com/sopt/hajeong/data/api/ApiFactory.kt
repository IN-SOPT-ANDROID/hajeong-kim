package com.sopt.hajeong.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    val authRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) //Json데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build() //레트로핏 구현체 완성!
    }
    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) //Json데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build() //레트로핏 구현체 완성!
    }
    val authService: AuthService = authRetrofit.create(AuthService::class.java)
    val followerService: FollowerService = reqresRetrofit.create(FollowerService::class.java)
}