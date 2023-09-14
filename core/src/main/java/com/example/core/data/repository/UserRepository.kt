package com.example.core.data.repository

import com.example.core.domain.User

interface UserRepository {
    suspend fun getAllUsers() : List<User>
    suspend fun getUserDetails(username: String) : User
}