package com.sopt.hajeong.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import com.sopt.hajeong.util.ItemDiffCallback
import org.sopt.sample.databinding.ItemHomeBodyBinding

class FollowerAdapter: ListAdapter<ResponseGetFollowerListDTO.Follower,FollowerAdapter.BodyViewHolder>(
    ItemDiffCallback<ResponseGetFollowerListDTO.Follower>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return BodyViewHolder(ItemHomeBodyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class BodyViewHolder(
        private val binding: ItemHomeBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(follower: ResponseGetFollowerListDTO.Follower) {
            Glide.with(this.binding.root)
                .load(follower.avatar)//여기서 url을 받아와야지
                .into(binding.ivProfile)//여기다 뷰바인딩 해야지
            binding.tvName.text=follower.firstName
            binding.tvEmail.text=follower.email
        }
    }
}
