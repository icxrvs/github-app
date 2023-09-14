package com.example.core.usecases

import com.example.core.data.repository.UserRepository
import com.example.core.domain.User
import com.example.core.usecases.base.UseCase
import com.example.core.util.Status
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepository) : UseCase<String, List<User>>() {
    override suspend fun doWork(params: String): Status<List<User>> {
        val result = userRepository.getAllUsers()
        return if (result.isNotEmpty()) {
            Status.Success(result)
        } else {
            Status.Error(Exception("No users found"))
        }
    }
}