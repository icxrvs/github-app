package com.example.githubapp.presentation.list_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.User
import com.example.core.usecases.GetAllUsersUseCase
import com.example.core.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
): ViewModel() {

    private val _users = MutableLiveData<Status<List<User>>>()
    val users: LiveData<Status<List<User>>> get() = _users

    fun getAllUsers() {
        viewModelScope.launch {
            getAllUsersUseCase("").collect { status ->
                _users.value = status
            }
        }
    }
}