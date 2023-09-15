package com.example.githubapp.framework

import com.example.core.data.repository.UserRemoteDataSource
import com.example.core.data.repository.UserRepository
import com.example.core.domain.User
import com.example.core.domain.UserRepositories
import com.example.githubapp.framework.network.response.UserRepositoriesResponse
import com.example.githubapp.framework.network.response.UserResponse
import com.example.githubapp.mappers.Mappers.toListUserModel
import com.example.githubapp.mappers.Mappers.toListUserRepositories
import com.example.githubapp.mappers.Mappers.toUserModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource<List<UserResponse>, UserResponse, List<UserRepositoriesResponse>>
): UserRepository {
    override suspend fun getAllUsers( ): List<User> = toListUserModel(remoteDataSource.getAllUsers())

    override suspend fun getUserDetails(username: String): User = toUserModel(remoteDataSource.getUser(username))

    override suspend fun getUserAllRepositories(username: String): List<UserRepositories> = toListUserRepositories(remoteDataSource.getUserAllRepositories(username))
}