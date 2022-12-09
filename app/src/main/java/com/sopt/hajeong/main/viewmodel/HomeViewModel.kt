package com.sopt.hajeong.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.api.FollowerService
import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import okhttp3.internal.threadName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel(){
    val _homeResult: MutableLiveData<ResponseGetFollowerListDTO> = MutableLiveData()
    val homeResult: LiveData<ResponseGetFollowerListDTO>
        get() = _homeResult
    private val followerService = ApiFactory.followerService

    fun getFollower() {
        // 팔로워 리스트 API 연결
        followerService.getFollowerList(page=1
        ).enqueue(object : Callback<ResponseGetFollowerListDTO> {
                override fun onResponse(
                    call: Call<ResponseGetFollowerListDTO>,
                    response: Response<ResponseGetFollowerListDTO>
                ) {
                    if (response.isSuccessful) {
                        Log.d("서버 통신 성공", response.message())
                        _homeResult.value = response.body()
                    } else {
                    }
                }
                override fun onFailure(call: Call<ResponseGetFollowerListDTO>, t: Throwable) {
                    Log.d("서버 통신 실패", t.toString())
                }
            })
    }
}