package com.sopt.hajeong.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.api.ApiFactory.musicService
import com.sopt.hajeong.data.model.ResponseMusicDTO
import com.sopt.hajeong.util.ContentUriRequestBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel : ViewModel() {
    private val service = musicService

    private val _getResult: MutableLiveData<ResponseMusicDTO> = MutableLiveData()
    val getResult: LiveData<ResponseMusicDTO>
        get() = _getResult
    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> = _successGet
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    fun getMusic() {
        service.getMusic().enqueue(object : Callback<ResponseMusicDTO> {
            override fun onResponse(
                call: Call<ResponseMusicDTO>,
                response: Response<ResponseMusicDTO>
            ) {
                when (response.code()) {
                    400 -> {
                        Log.e("400", "서버 통신 에러")
                        _successGet.value = false
                    }
                    500 -> {
                        Log.e("500", "서버 통신 에러")
                        _successGet.value = false
                    }
                }
                if (response.isSuccessful) {
                    _getResult.value = response.body()
                    _successGet.value = true
                }
            }

            override fun onFailure(call: Call<ResponseMusicDTO>, t: Throwable) {
                Log.e("fail", "서버 통신 실패")
                _successGet.value = false
                _errorMessage.value = t.message
            }
        })
    }
}