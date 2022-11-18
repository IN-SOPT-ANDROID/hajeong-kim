package com.sopt.hajeong.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            if (binding.etId.text.length >= 6 && binding.etId.text.length <= 10 && binding.etPw.text.length >= 8 && binding.etPw.text.length <= 12 && binding.etMbti.text.length == 4) {
                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id",binding.etId.text.toString())
                intent.putExtra("pw",binding.etPw.text.toString())
                intent.putExtra("mbti",binding.etMbti.text.toString())
                setResult(Activity.RESULT_OK,intent)

                finish()
            } else {
                Snackbar.make(binding.root, "아이디/비밀번호를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
