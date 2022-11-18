package com.sopt.hajeong.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.sopt.hajeong.data.ApiFactory
import com.sopt.hajeong.data.ApiFactory.followerService
import com.sopt.hajeong.data.FollowerService
import com.sopt.hajeong.data.ResponseGetFollowerListDTO
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding //var 불변변수 val 가변변수
        get() = _binding!!
    private val followerService: FollowerService = ApiFactory.followerService

    override fun onCreateView( //onCreateView: 뷰껍데기 만들기
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 리사이클러뷰 어댑터
        val adapter = FollowerAdapter(requireContext())
        binding.rvHome.adapter = adapter
        // 팔로워 목록 API 연결
        followerService.getFollowerList().enqueue(object : Callback<ResponseGetFollowerListDTO> {
            override fun onResponse(
                call: Call<ResponseGetFollowerListDTO>,
                response: Response<ResponseGetFollowerListDTO>
            ) {
                //get 성공
                if (response.isSuccessful) {
                    Log.e("success","서버는 성공임")
                    response.body()?.let {
                        adapter.setFollowerList(it.data)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseGetFollowerListDTO>, t: Throwable) {
                //get 실패(서버통신 오류)
                Log.e("asdf", "message : " + t.message)
            }
        })
    }
}

