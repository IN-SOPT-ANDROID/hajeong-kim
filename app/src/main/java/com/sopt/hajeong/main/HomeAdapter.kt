package com.sopt.hajeong.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeBodyBinding
import org.sopt.sample.databinding.ItemHomeHeaderBinding

class HomeAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val Header = 0
    private val Body = 1
    val userList = mutableListOf<UserData>()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            Header
        } else Body
    }

    inner class HeaderViewHolder( //inner class: class 안에 class
        private val binding: ItemHomeHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {}

    inner class BodyViewHolder(
        private val binding: ItemHomeBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.ivCat.setImageResource(data.image)
            binding.tvTitle.text = data.title
            binding.tvName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val binding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
        //return RepoViewHolder(binding)
        return when (viewType) {
            Header -> {
                val itemHomeHeaderBinding = ItemHomeHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(itemHomeHeaderBinding)
            }
            else -> {
                val itemHomeBodyBinding =
                    ItemHomeBodyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BodyViewHolder(itemHomeBodyBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BodyViewHolder) {
            holder.onBind(userList[position])
        } else if (holder is HeaderViewHolder) {
            holder
        }
    }

    override fun getItemCount(): Int = userList.size
}
