package com.example.core.data.repository

import com.example.core.domain.User
import com.example.core.domain.UserRepositories

interface UserRepository {
    suspend fun getAllUsers() : List<User>
    suspend fun getUserDetails(username: String) : User
    suspend fun getUserAllRepositories(username: String) : List<UserRepositories>
}