package com.sopt.hajeong.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sopt.hajeong.main.viewmodel.SignUpViewModel
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = viewModel

        binding.btnSignup.isEnabled = false
        signupBtnOnClick()
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            viewModel.signup(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
        }
        viewModel.signupResult.observe(this) {
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java)) //화면전환
            finish()
        }
        viewModel.idLiveData.observe(this) {
            binding.tvIdWarn.isVisible = !it
        }
        viewModel.pwLiveData.observe(this) {
            binding.tvPwWarn.isVisible = !it
        }
    }
}
