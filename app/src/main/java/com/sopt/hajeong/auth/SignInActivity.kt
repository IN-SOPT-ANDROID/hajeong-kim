package com.sopt.hajeong.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String? = null
    private var pw: String? = null
    private var mbti: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    id = it.data?.getStringExtra("id") ?: ""
                    pw = it.data?.getStringExtra("pw") ?: ""
                    mbti = it.data?.getStringExtra("mbti") ?: ""
                }
            }
        binding.btnLogin.setOnClickListener {
            if (binding.etId.text.toString() == id && binding.etPw.text.toString() == pw) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SelfIntroduceActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("mbti",mbti)
                setResult(Activity.RESULT_OK,intent)

                startActivity(intent)

                finish()
            }
            else{
                Toast.makeText(this,"다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignup.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}