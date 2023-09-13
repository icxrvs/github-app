package com.example.core.data.network

import com.example.core.data.network.response.UserResponse
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
