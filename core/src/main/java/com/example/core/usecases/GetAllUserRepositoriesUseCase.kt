package com.example.core.usecases

import com.example.core.data.repository.UserRepository
import com.example.core.domain.UserRepositories
import com.example.core.usecases.base.UseCase
import com.example.core.util.Status
import javax.inject.Inject

class GetAllUserRepositoriesUseCase @Inject constructor(private val userRepository: UserRepository) : UseCase<String, List<UserRepositories>>() {
    override suspend fun doWork(params: String): Status<List<UserRepositories>> {
        val result = userRepository.getUserAllRepositories(params)
        return Status.Success(result)
    }
}