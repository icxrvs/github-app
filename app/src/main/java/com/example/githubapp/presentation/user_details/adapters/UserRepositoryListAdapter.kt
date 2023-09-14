package com.example.githubapp.presentation.user_details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.UserRepositories
import com.example.githubapp.R
import com.example.githubapp.databinding.UserRepositoryItemBinding

class UserRepositoryListAdapter: ListAdapter<UserRepositories, UserRepositoryListAdapter.CustomViewHolder>(UserRepositoryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val binding = UserRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class CustomViewHolder(private val viewBinding: UserRepositoryItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bindTo(userRepositories: UserRepositories){
            viewBinding.apply {
                repositoryName.text = userRepositories.name
                if (!userRepositories.description.isNullOrEmpty()){
                    repositoryDescription.text = userRepositories.description
                }else
                    repositoryDescription.text = viewBinding.root.context.getString(R.string.empty_description)
            }
        }
    }

    class UserRepositoryItemDiffCallback : DiffUtil.ItemCallback<UserRepositories>() {
        override fun areItemsTheSame(oldItem: UserRepositories, newItem: UserRepositories): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: UserRepositories, newItem: UserRepositories): Boolean = oldItem == newItem
    }
}