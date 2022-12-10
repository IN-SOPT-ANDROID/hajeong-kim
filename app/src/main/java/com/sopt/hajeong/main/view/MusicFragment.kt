package com.sopt.hajeong.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.hajeong.main.adapter.MusicAdapter
import com.sopt.hajeong.main.viewmodel.MusicViewModel
import org.sopt.sample.databinding.FragmentSearchBinding

class MusicFragment : Fragment() {
    private val musicViewModel by viewModels<MusicViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicViewModel.getMusic()
        val adapter = MusicAdapter()
        binding.rvMusicList.adapter = adapter
    }
}