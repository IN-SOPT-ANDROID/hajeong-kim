package com.sopt.hajeong.auth

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.hajeong.data.*
import com.sopt.hajeong.main.MainActivity
import org.sopt.sample.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var email: String? = null
    private var pw: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    email = it.data?.getStringExtra("email") ?: ""
                    pw = it.data?.getStringExtra("pw") ?: ""
                }
            }
        signinBtnOnClick()
        signupBtnOnClick()
    }

        private fun signinBtnOnClick(){
            binding.btnLogin.setOnClickListener {
                val requestloginDTO = RequestLoginDTO(
                    binding.etEmail.text.toString(),
                    binding.etPw.text.toString()
                )

                //이메일을 입력하지 않은 경우
                if (binding.etEmail.text.isEmpty()) {
                    Toast.makeText(this, "이메일을 입력하세요.",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //잘못된 비밀번호를 입력한 경우 (성공조건 : 6자 이상 10자 이하)
                if (binding.etPw.text.length !in 6..10){
                    Toast.makeText(this, "비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //로그인 API 연결
                val loginService : Call<ResponseLoginDTO> = ApiFactory.authService.login(requestloginDTO)

                loginService.enqueue(object : Callback<ResponseLoginDTO> { //입력값은 login(), 출력값은 Callback<>
                    override fun onResponse(
                        call: Call<ResponseLoginDTO>,
                        response: Response<ResponseLoginDTO>
                    ) {
                        //로그인 성공
                        if (response.isSuccessful) {
                            Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT)
                                .show()

                            val intent = Intent(this@SignInActivity, MainActivity::class.java).apply{
                                putExtra("id", response.body()?.result?.id)
                            }
                            startActivity(intent) //화면전환
                            finish()
                        }
                        //로그인 실패
                        else {
                            Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                        //서버통신 오류
                        Log.e("asdf", "${t}")
                        Toast.makeText(this@SignInActivity, "에러 발생", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    private fun signupBtnOnClick() { //signup버튼을 누르면 넘어가게 설정
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}