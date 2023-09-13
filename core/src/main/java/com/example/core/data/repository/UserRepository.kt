package com.example.core.data.repository

import com.example.core.domain.User

interface UserRepository {
    suspend fun getUsers(query: String) : List<User>
}