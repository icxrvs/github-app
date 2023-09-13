package com.example.githubapp.framework


import com.example.core.data.repository.UserRemoteDataSource
import com.example.core.data.repository.UserRepository
import com.example.core.domain.User
import com.example.githubapp.mappers.UserMapper.toListUser
import com.example.githubapp.framework.network.response.UserResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource<List<UserResponse>>
): UserRepository {
    override suspend fun getUsers(query: String): List<User>{
        return toListUser(remoteDataSource.getUsers(query))
    }
}