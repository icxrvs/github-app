package com.example.githubapp.framework.network

import com.example.githubapp.framework.network.response.UserRepositoriesResponse
import com.example.githubapp.framework.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getUserAllRepositories(@Path("username") username: String): List<UserRepositoriesResponse>
}
