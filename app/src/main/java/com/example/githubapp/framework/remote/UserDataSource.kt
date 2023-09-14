package com.example.githubapp.framework.remote

import com.example.core.data.repository.UserRemoteDataSource
import com.example.githubapp.framework.network.GithubApi
import com.example.githubapp.framework.network.response.UserRepositoriesResponse
import com.example.githubapp.framework.network.response.UserResponse
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val githubApi: GithubApi
): UserRemoteDataSource<List<UserResponse>, UserResponse, List<UserRepositoriesResponse>>{

    override suspend fun getAllUsers(): List<UserResponse> = githubApi.getAllUsers()
    override suspend fun getUser(username: String): UserResponse = githubApi.getUser(username)
    override suspend fun getUserAllRepositories(username: String): List<UserRepositoriesResponse> = githubApi.getUserAllRepositories(username)

}