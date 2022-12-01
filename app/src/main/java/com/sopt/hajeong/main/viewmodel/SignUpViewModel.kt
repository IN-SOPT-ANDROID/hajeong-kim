package com.sopt.hajeong.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.api.ApiFactory.authService
import com.sopt.hajeong.data.model.RequestSignupDTO
import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import com.sopt.hajeong.data.model.ResponseSignupDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signupResult: MutableLiveData<ResponseSignupDTO> = MutableLiveData()
    val signupResult: LiveData<ResponseSignupDTO>
        get() = _signupResult
    private var authService = ApiFactory.authService

    val inputId = MutableLiveData("")
    val inputPw = MutableLiveData("")
    val inputName = MutableLiveData("")

    val idLiveData: LiveData<Boolean> = Transformations.map(inputId) { inputId ->
        val result = checkId(inputId)
        Log.d("아이디", result.toString())
        result
    }
    val pwLiveData: LiveData<Boolean> = Transformations.map(inputPw) { inputPw ->
        val result = checkPw(inputPw)
        result
    }

    fun signup(email: String, pw: String, name: String) {
        authService.signup(
            RequestSignupDTO(email, pw, name)
        ).enqueue(object : Callback<ResponseSignupDTO> {
            override fun onResponse(
                call: Call<ResponseSignupDTO>,
                response: Response<ResponseSignupDTO>
            ) {
                if (response.isSuccessful) {
                    Log.d("서버 통신 성공", response.message())
                    _signupResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignupDTO>, t: Throwable) {
                Log.d("서버 통신 실패", t.toString())
            }
        })
    }

    //정규표현식
    private fun checkId(id: String): Boolean {
        Log.d("아이디", id)
        val regex = Regex("^[a-zA-Z0-9]{6,10}$")
        return id.matches(regex) || id.isEmpty()
    }

    private fun checkPw(pw: String): Boolean {
        val regexWithNumAndEngAndSpecificWord = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{6,12}$")
        return pw.matches(regexWithNumAndEngAndSpecificWord) || pw.isEmpty()
    }
}