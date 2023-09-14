package com.example.githubapp.presentation.list_user.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.User
import com.example.githubapp.databinding.UserListItemBinding

class UserListAdapter(private val onUserClick: (User) -> Unit): ListAdapter<User, UserListAdapter.CustomViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position), onUserClick)
    }

    class CustomViewHolder(private val viewBinding: UserListItemBinding) : RecyclerView.ViewHolder(viewBinding.root){

        fun bindTo(user:User, onUserClick: (User) -> Unit){
            viewBinding.apply {
                txUserName.text = user.login
                root.setOnClickListener {
                    onUserClick.invoke(user)
                }
            }
        }
    }

    class UserItemDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}