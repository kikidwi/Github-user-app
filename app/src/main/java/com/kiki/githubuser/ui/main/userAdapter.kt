package com.kiki.githubuser.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kiki.githubuser.databinding.ItemBinding
import com.kiki.githubuser.model.user


class userAdapter : RecyclerView.Adapter<userAdapter.userViewHolder>(){

    private val list = ArrayList<user>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users : ArrayList<user>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class userViewHolder (val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (user : user){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply{
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUser)
                tvNameUser.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return userViewHolder((view))
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: user)
    }
}