package com.sopt.hajeong.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.databinding.ActivitySelfIntroduceBinding

class SelfIntroduceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelfIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val mbti = intent.getStringExtra("mbti")

        binding.tvGetName.text= "$id"
        binding.tvGetMbti.text= "$mbti"
    }
}