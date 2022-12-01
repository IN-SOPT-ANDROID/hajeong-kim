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

    val inputId = MutableLiveData<String>()
    val inputPw = MutableLiveData<String>()
    val inputName = MutableLiveData("")

    val idLiveData: LiveData<Boolean> = Transformations.map(inputId) { inputId ->
        Log.d("성공", "${inputId}")
        checkId(inputId) }
    val pwLiveData: LiveData<Boolean> = Transformations.map(inputPw) { inputPw ->
        checkPw(inputPw) }

    fun signup(email:String, pw:String, name:String){
        authService.signup(RequestSignupDTO(email, pw, name)
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
        return id.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z[0-9]].{6,10}$".toRegex()) || id.isNullOrEmpty()
    }

    private fun checkPw(pw: String): Boolean {
        return pw.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[A-Za-z[0-9]$!@#\$%^&*].{6,12}$".toRegex()) || pw.isNullOrEmpty()
    }
}