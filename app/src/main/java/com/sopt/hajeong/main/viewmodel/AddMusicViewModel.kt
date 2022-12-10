package com.sopt.hajeong.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.model.ResponseMusicDTO
import com.sopt.hajeong.util.ContentUriRequestBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMusicViewModel: ViewModel() {
    private val addMusicService = ApiFactory.addMusicService
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun postMusic(map: HashMap<String, RequestBody>) {
        addMusicService.postMusic(
            image.value!!.toFormData(),
            map
        ).enqueue(object : Callback<ResponseMusicDTO> {
            override fun onResponse(
                call: Call<ResponseMusicDTO>,
                response: Response<ResponseMusicDTO>
            ) {
                when (response.code()) {
                    400 -> {
                        Log.e("map: ", map["singer"].toString())
                        Log.e("map: ", map["title"].toString())
                        Log.e("400", "서버 통신 에러")
                    }
                    500 -> {
                        Log.e("500", "서버 통신 에러")
                    }
                }
                if (response.isSuccessful) {
                    Log.d("success", "서버 통신 성공")
                }
            }

            override fun onFailure(call: Call<ResponseMusicDTO>, t: Throwable) {
                Log.e("fail", "서버 통신 실패")
            }
        })
    }
}