package com.fridaaltunyan.newsapp.di

import androidx.paging.Pager
import com.fridaaltunyan.newsapp.data.local.NewsItemEntity
import com.fridaaltunyan.newsapp.data.repo.NewsRepositoryImpl
import com.fridaaltunyan.newsapp.domain.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsPager: Pager<Int, NewsItemEntity>,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): NewsRepository = NewsRepositoryImpl(newsPager, dispatcher)
}