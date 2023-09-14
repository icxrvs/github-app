package com.example.githubapp.presentation.list_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.User
import com.example.core.util.Status
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentHomeBinding
import com.example.githubapp.presentation.list_user.adapters.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding
    private val viewModel: UsersViewModel by viewModels()
    private var adapter : UserListAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter = UserListAdapter{
            val userName = it.login
            val action = userName?.let { login ->
                UsersFragmentDirections.actionNavigationHomeToUserDetailFragment(
                    login
                )
            }

            if (action != null) {
                findNavController(this).navigate(action)
            }
        }

        viewModel.getAllUsers()

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {

            fabButton.setOnClickListener {
                buildAlertDialog()
            }

            viewModel.users.observe(viewLifecycleOwner) { status ->
                when (status) {
                    is Status.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Status.Success<*> -> {
                        progressBar.visibility = View.GONE
                        val usersList = status.data as? List<User>
                        if (!usersList.isNullOrEmpty()) {
                            rvUsers.adapter = adapter
                            adapter?.submitList(usersList)
                            rvUsers.layoutManager = LinearLayoutManager(context)

                            rvUsers.addItemDecoration(
                                DividerItemDecoration(
                                    context,
                                   1
                                )
                            )
                            rvUsers.visibility = View.VISIBLE
                        }
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

    private fun buildAlertDialog(){
        val builder = context?.let { AlertDialog.Builder(it) }

        builder?.setTitle(getString(R.string.search_user))

        val customView = LayoutInflater.from(context).inflate(R.layout.dialog_input, null)
        val editText = customView.findViewById<EditText>(R.id.editText)

        builder?.setView(customView)

        builder?.setPositiveButton(getString(R.string.text_confirm)) { dialog, which ->
            val userName = editText.text.toString()
            val action = UsersFragmentDirections.actionNavigationHomeToUserDetailFragment(userName)
            findNavController(this).navigate(action)
        }

        builder?.setNegativeButton(getString(R.string.text_cancel)) { dialog, which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
}