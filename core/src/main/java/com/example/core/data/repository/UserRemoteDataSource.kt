package com.example.core.data.repository

interface UserRemoteDataSource<T> {
    suspend fun getUsers(query: String): T
}