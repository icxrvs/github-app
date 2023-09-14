package com.example.githubapp.framework.network

import com.example.githubapp.framework.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>
}
