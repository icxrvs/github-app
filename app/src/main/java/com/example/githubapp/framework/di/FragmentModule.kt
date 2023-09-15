package com.example.githubapp.framework.di

import androidx.fragment.app.FragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FragmentModule {
    @Provides
    @Singleton
    fun provideFragmentFactory(): FragmentFactory {
        // If you have a custom FragmentFactory, instantiate and return it here
        return FragmentFactory()
    }
}