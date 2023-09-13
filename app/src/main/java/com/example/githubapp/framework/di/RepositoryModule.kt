package com.example.githubapp.framework.di

import com.example.core.data.repository.UserRemoteDataSource
import com.example.core.data.repository.UserRepository
import com.example.githubapp.framework.UserRepositoryImpl
import com.example.githubapp.framework.network.response.UserResponse
import com.example.githubapp.framework.remote.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        fun provideRemoteDataSource(dataSource: UserDataSource): UserRemoteDataSource<List<UserResponse>> {
            return dataSource
        }
    }
}