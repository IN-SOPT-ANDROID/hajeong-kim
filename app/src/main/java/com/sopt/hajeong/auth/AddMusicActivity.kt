package com.sopt.hajeong.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import coil.load
import com.sopt.hajeong.main.view.MainActivity
import com.sopt.hajeong.main.view.MusicFragment
import com.sopt.hajeong.main.viewmodel.AddMusicViewModel
import com.sopt.hajeong.util.ContentUriRequestBody
import kotlinx.serialization.json.JsonNull.content
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.sample.databinding.ActivityAddMusicBinding

class AddMusicActivity : AppCompatActivity() {
    private val viewModel by viewModels<AddMusicViewModel>()
    private lateinit var binding: ActivityAddMusicBinding
    private val map: HashMap<String, RequestBody> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AddMusicBtnOnClick()
    }

    private fun AddMusicBtnOnClick() {
        binding.btnAddMusic.setOnClickListener {
            val title = binding.etMusicTitle.text.toString()
            val singer = binding.etMusicSinger.text.toString()
            map["request"] =
                "{\"title\": \"$title\", \"singer\": \"$singer\"}".toRequestBody("application/json".toMediaTypeOrNull())
            viewModel.postMusic(map!!)
        }
        musicListBtnOnClick()
    }

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            binding.ivMusicPic.load(it)
            viewModel.setRequestBody(ContentUriRequestBody(requireContext(), it))
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    private fun musicListBtnOnClick() { //music list버튼을 누르면 music list 페이지로 넘어가게 설정
        binding.btnMusicList.setOnClickListener {
            val intent = Intent(this, MusicFragment::class.java)
            startActivity(intent)
        }
    }
}