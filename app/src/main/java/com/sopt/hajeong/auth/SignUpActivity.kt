package com.sopt.hajeong.auth

import android.content.Intent
import android.graphics.Color
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
        binding.btnSignup.isEnabled = false

        binding.vm = viewModel
        signupBtnOnClick()
        setObserver()
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            viewModel.signup(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
        }
    }
    private fun setObserver(){
        viewModel.signupResult.observe(this) {
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java)) //화면전환
            finish()
        }
        viewModel.idLiveData.observe(this) {
            binding.tvIdWarn.isVisible = !it //it은 LiveData의 value
        }
        viewModel.pwLiveData.observe(this) {
            binding.tvPwWarn.isVisible = !it
        }
        viewModel.btnLiveData.observe(this) {
            binding.btnSignup.isEnabled = it
        }
    }

}
