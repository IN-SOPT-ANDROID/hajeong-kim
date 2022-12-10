package com.sopt.hajeong.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import com.sopt.hajeong.data.model.ResponseMusicDTO
import com.sopt.hajeong.util.ItemDiffCallback
import org.sopt.sample.databinding.ItemHomeBodyBinding
import org.sopt.sample.databinding.ItemMusicBodyBinding

class MusicAdapter: ListAdapter<ResponseMusicDTO.Music, MusicAdapter.MusicViewHolder>(
    ItemDiffCallback<ResponseMusicDTO.Music>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return MusicViewHolder(ItemMusicBodyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class MusicViewHolder(
        private val binding: ItemMusicBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(music: ResponseMusicDTO.Music) {
            Glide.with(this.binding.root)
                .load(music.image)//여기서 url을 받아와야지
                .into(binding.ivMusic)//여기다 뷰바인딩 해야지
            binding.tvTitle.text=music.title
            binding.tvSinger.text=music.singer
        }
    }
}