package com.example.githubapp.presentation.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.core.domain.User
import com.example.core.util.Status
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentUserDetailBinding
import com.example.githubapp.presentation.list_user.adapters.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentUserDetailBinding
    private val viewModel: UserViewModel by viewModels()
    private var adapter : UserListAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentUserDetailBinding.inflate(inflater, container, false)

        adapter = UserListAdapter()

        val args: UserDetailFragmentArgs by navArgs()
        val username = args.username

        viewModel.getUserDetail(username)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            viewModel.user.observe(viewLifecycleOwner) { status ->
                when (status) {
                    is Status.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Status.Success<*> -> {
                        progressBar.visibility = View.GONE
                        mountCard(status.data as User)
                    }
                    is Status.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context,getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()

                    }
                }
            }
        }
    }

    private fun mountCard(user: User?) {
        viewBinding.apply {
            cardView.visibility = View.VISIBLE
            context?.let {
                Glide.with(it).load(user?.avatarUrl).centerCrop().transform(CircleCrop()).into(userImage)
            }
            userName.text = user?.name
            userBio.text = user?.bio
        }
    }
}