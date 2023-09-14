package com.example.githubapp.presentation.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.util.Status
import com.example.githubapp.databinding.FragmentHomeBinding
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

        viewModel.getUserDetail("icarusdevv")

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
                        Toast.makeText(context, "Deu certo!", Toast.LENGTH_SHORT).show()

                    }
                    is Status.Error -> {
                        progressBar.visibility = View.GONE
                        val errorMessage = status.throwable.message
                        println(errorMessage)
                    }
                }
            }
        }
    }
}