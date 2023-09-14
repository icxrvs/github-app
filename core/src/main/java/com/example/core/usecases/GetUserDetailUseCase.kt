package com.example.core.usecases

import com.example.core.data.repository.UserRepository
import com.example.core.domain.User
import com.example.core.usecases.base.UseCase
import com.example.core.util.Status
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val userRepository: UserRepository) : UseCase<String, User>() {
    override suspend fun doWork(params: String): Status<User> {
        val result = userRepository.getUserDetails(params)
        return Status.Success(result)
    }
}