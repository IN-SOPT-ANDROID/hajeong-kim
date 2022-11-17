package com.sopt.hajeong.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.sopt.hajeong.data.*
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var email: String? = null
    private var pw: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    email = it.data?.getStringExtra("email") ?: ""
                    pw = it.data?.getStringExtra("pw") ?: ""
                    name = it.data?.getStringExtra("name") ?: ""
                }
            }

        signupBtnOnClick()
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            val requestSignupDTO = RequestSignupDTO(
                binding.etEmail.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )

            // 회원가입 API 연결
            val signupService : Call<ResponseSignupDTO> =ApiFactory.authService.signup(requestSignupDTO)

            signupService.enqueue(object : Callback<ResponseSignupDTO> {
                override fun onResponse(
                    call: Call<ResponseSignupDTO>,
                    response: Response<ResponseSignupDTO>
                ) {
                    //회원가입 성공
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT)
                            .show()

                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply{
                            putExtra("id", response.body()?.result?.id)
                        }
                        startActivity(intent) //화면전환
                        finish()
                    }
                    //회원가입 실패
                    else {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDTO>, t: Throwable) {
                    //서버통신 오류
                    Log.e("asdf", "${t}")
                    Toast.makeText(this@SignUpActivity, "에러 발생", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}