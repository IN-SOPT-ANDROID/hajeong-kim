package com.sopt.hajeong.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding //var 불변변수 val 가변변수
        get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    //lateinit: 지연초기화를 위해 필요
    //중간에 접근할 때 초기화하기 위해 사용(앞에 선언했다해도 초기화를 미룰수 있음)

    override fun onCreateView( //onCreateView: 뷰껍데기 만들기
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvHome.adapter = homeAdapter //homeAdapter를 rvHome(리사이클러뷰)과 연결
        initUserData()
    }

    private fun initUserData(){
        homeAdapter.userList.addAll(
            listOf( //데이터 조작
                UserData(0,"",""),
                UserData(R.drawable.cat,"Android1","youngju"),
                UserData(R.drawable.cat,"Android2","daehwan"),
                UserData(R.drawable.cat,"Android3","hajeong"),
                UserData(R.drawable.cat,"Android4","jieun"),
                UserData(R.drawable.cat,"Android5","a"),
                UserData(R.drawable.cat,"Android6","b"),
                UserData(R.drawable.cat,"Android7","c"),
                UserData(R.drawable.cat,"Android8","d"),
                UserData(R.drawable.cat,"Android9","e"),
                UserData(R.drawable.cat,"Android10","f"),
                UserData(R.drawable.cat,"Android11","g"),
                UserData(R.drawable.cat,"Android12","h"),
                UserData(R.drawable.cat,"Android13","i"),
                UserData(R.drawable.cat,"Android14","j"),
                UserData(R.drawable.cat,"Android15","k")
            )
        )
        homeAdapter.notifyDataSetChanged() //**리스트 업데이트를 알려줌
    }

    fun viewTop() {
        binding.rvHome.scrollToPosition(0)
    }//리사이클러뷰의 최상단으로 이동

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}