package com.example.githubapp.presentation.user_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.User
import com.example.core.usecases.GetUserDetailUseCase
import com.example.core.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
): ViewModel() {

    private val _user = MutableLiveData<Status<User>>()
    val user: LiveData<Status<User>> get() = _user

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            getUserDetailUseCase(username).collect { status ->
                _user.value = status
            }
        }
    }
}