package com.fridaaltunyan.newsapp.di

import com.fridaaltunyan.newsapp.data.repo.NewsRepositoryImpl
import com.fridaaltunyan.newsapp.domain.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}