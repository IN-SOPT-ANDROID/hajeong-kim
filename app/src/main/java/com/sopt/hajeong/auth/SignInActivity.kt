package com.sopt.hajeong.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.sopt.hajeong.data.api.ApiFactory
import com.sopt.hajeong.data.model.RequestLoginDTO
import com.sopt.hajeong.data.model.ResponseLoginDTO
import com.sopt.hajeong.main.view.MainActivity
import com.sopt.hajeong.main.viewmodel.SignInViewModel
import com.sopt.hajeong.main.viewmodel.SignUpViewModel
import org.sopt.sample.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etId.text.toString(),
                binding.etPw.text.toString()
            )
        }
        viewModel.signinResult.observe(this){
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        signinBtnOnClick()
        signupBtnOnClick()
    }

    private fun signinBtnOnClick() {
        binding.btnLogin.setOnClickListener {
            //이메일을 입력하지 않은 경우
            if (binding.etId.text.isEmpty()) {
                Toast.makeText(this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //잘못된 비밀번호를 입력한 경우 (성공조건 : 6자 이상 10자 이하)
            fun EditText.isPasswordValid() = text.length !in 6..10

            //if (binding.etPw.text.length !in 6..10){
            if (binding.etPw.isPasswordValid()) {
                Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }

    private fun signupBtnOnClick() { //signup버튼을 누르면 넘어가게 설정
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}