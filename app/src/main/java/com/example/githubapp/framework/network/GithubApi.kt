package com.example.githubapp.framework.network

import com.example.githubapp.framework.network.response.UserResponse
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    suspend fun getUsers(query: String): List<UserResponse>
}
