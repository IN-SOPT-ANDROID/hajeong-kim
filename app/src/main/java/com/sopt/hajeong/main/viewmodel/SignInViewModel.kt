package com.sopt.hajeong.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.api.ApiFactory.authService
import com.sopt.hajeong.data.model.RequestLoginDTO
import com.sopt.hajeong.data.model.ResponseLoginDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
        private val _signinResult: MutableLiveData<ResponseLoginDTO> = MutableLiveData()
        val signinResult: LiveData<ResponseLoginDTO>
            get() = _signinResult
        private var authService = ApiFactory.authService

        fun login(email:String, pw:String) {
            authService.login(
                RequestLoginDTO(email, pw)
            ).enqueue(object : Callback<ResponseLoginDTO> {
                override fun onResponse(
                    call: Call<ResponseLoginDTO>,
                    response: Response<ResponseLoginDTO>
                ) {
                    if (response.isSuccessful) {
                        Log.d("서버 통신 완료", response.message())
                        _signinResult.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                    Log.d("서버 통신 실패", t.toString())
                }

            })
        }
}