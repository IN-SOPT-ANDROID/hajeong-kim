package org.sopt.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.databinding.ActivitySelfIntroduceBinding
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.databinding.ActivitySignUpBinding

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