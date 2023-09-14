package com.example.githubapp.framework

import com.example.core.data.repository.UserRemoteDataSource
import com.example.core.data.repository.UserRepository
import com.example.core.domain.User
import com.example.githubapp.framework.network.response.UserResponse
import com.example.githubapp.mappers.UserMapper.toListUserModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource<List<UserResponse>, UserResponse>
): UserRepository {
    override suspend fun getAllUsers( ): List<User>{
        return toListUserModel(remoteDataSource.getAllUsers())
    }

    override suspend fun getUserDetails(username: String): User {
        TODO("Not yet implemented")
    }
}