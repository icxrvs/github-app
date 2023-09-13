package com.example.core.data.repository

interface UsersRemoteDataSource<T> {
    suspend fun getUsers(query: String): T
}