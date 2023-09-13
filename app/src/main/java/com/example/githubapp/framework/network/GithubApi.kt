package com.example.githubapp.framework.network

import com.example.githubapp.framework.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("users")
    suspend fun getUsers(@Query("user") query: String): List<UserResponse>
}
