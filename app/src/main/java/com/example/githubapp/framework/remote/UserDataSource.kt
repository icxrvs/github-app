package com.example.githubapp.framework.remote

import com.example.core.data.repository.UserRemoteDataSource
import com.example.githubapp.framework.network.GithubApi
import com.example.githubapp.framework.network.response.UserResponse
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val githubApi: GithubApi
): UserRemoteDataSource<List<UserResponse>> {

    override suspend fun getUsers(query: String): List<UserResponse> {
        return githubApi.getUsers(query)
    }
}