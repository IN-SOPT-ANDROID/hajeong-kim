package com.sopt.hajeong.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.api.FollowerService
import com.sopt.hajeong.main.adapter.FollowerAdapter
import org.sopt.sample.databinding.FragmentHomeBinding

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
        val adapter = FollowerAdapter()
        binding.rvHome.adapter = adapter
    }
}

