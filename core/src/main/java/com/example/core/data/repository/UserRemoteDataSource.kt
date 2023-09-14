package com.example.core.data.repository

interface UserRemoteDataSource<T1, T2> {
    suspend fun getAllUsers(): T1
    suspend fun getUser(username: String): T2
}