package com.example.githubapp.presentation.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.core.domain.User
import com.example.core.domain.UserRepositories
import com.example.core.util.Status
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentUserDetailBinding
import com.example.githubapp.presentation.list_user.adapters.UserListAdapter
import com.example.githubapp.presentation.user_details.adapters.UserRepositoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var viewBinding: FragmentUserDetailBinding
    private val viewModel: UserDetailsViewModel by viewModels()
    private var adapter: UserRepositoryListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentUserDetailBinding.inflate(inflater, container, false)

        adapter = UserRepositoryListAdapter()

        val args: UserDetailsFragmentArgs by navArgs()

        val username = args.username

        viewModel.getUserDetail(username)
        viewModel.getAllUserRepositories(username)

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
                        Toast.makeText(
                            context,
                            getString(R.string.user_not_found),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()

                    }
                }
            }

            viewModel.userRepositories.observe(viewLifecycleOwner) { status ->
                when (status) {
                    is Status.Loading -> {
                        progressBarRepositories.visibility = View.VISIBLE
                    }

                    is Status.Success<*> -> {
                        progressBarRepositories.visibility = View.GONE
                        mountRepositoriesList(status.data as List<UserRepositories>)
                    }

                    is Status.Error -> {
                        progressBarRepositories.visibility = View.GONE
                        Toast.makeText(context, status.throwable.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun mountRepositoriesList(userRepositoriesList: List<UserRepositories>) {
        viewBinding.apply {
            if (userRepositoriesList.isNotEmpty()) {
                rvRepositories.adapter = adapter
                adapter?.submitList(userRepositoriesList)
                rvRepositories.layoutManager = LinearLayoutManager(context)

                rvRepositories.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        1
                    )
                )
                rvRepositories.visibility = View.VISIBLE

            } else {
                Toast.makeText(
                    context,
                    getString(R.string.user_doesnt_have_repositories), Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    @SuppressLint("StringFormatMatches")
    private fun mountCard(user: User?) {
        viewBinding.apply {
            cardView.visibility = View.VISIBLE
            context?.let {
                Glide.with(it).load(user?.avatarUrl).centerCrop().transform(CircleCrop())
                    .into(userImage)
            }
            userName.text = user?.name
            userBio.text = user?.bio
            userFollowers.text = getString(R.string.followers, user?.followers)
            userFollowing.text = getString(R.string.following, user?.following)
        }
    }
}