package com.example.core.data.repository

import androidx.paging.PagingSource
import com.example.core.domain.User

interface UsersRepository {
    fun getUsers(query: String) : PagingSource<Int, User>
}