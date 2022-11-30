package com.sopt.hajeong.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.hajeong.data.model.ResponseGetFollowerListDTO
import org.sopt.sample.databinding.ItemHomeBodyBinding

class FollowerAdapter(context: Context) : RecyclerView.Adapter<FollowerAdapter.BodyViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList: List<ResponseGetFollowerListDTO.Follower> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder {
        val binding = ItemHomeBodyBinding.inflate(inflater, parent, false)
        return BodyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    fun setFollowerList(followerList: List<ResponseGetFollowerListDTO.Follower>) {
        this.followerList = followerList.toList()
        notifyDataSetChanged() //갱신처리(리스트 업데이트)
    }


    class BodyViewHolder(
        private val binding: ItemHomeBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(follower: ResponseGetFollowerListDTO.Follower) {
            Glide.with(this.binding.root)
                .load(follower.avatar)//여기서 url을 받아와야지
                .into(binding.ivProfile)//여기서 뷰바인딩 해야지
            binding.tvName.text=follower.firstName
            binding.tvEmail.text=follower.email
        }
    }
}
