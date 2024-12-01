package com.fridaaltunyan.newsapp.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.fridaaltunyan.newsapp.data.local.NewsDatabase
import com.fridaaltunyan.newsapp.data.local.NewsItemEntity
import com.fridaaltunyan.newsapp.data.remote.NewsApi
import com.fridaaltunyan.newsapp.data.remote.NewsRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news.db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideQueryHolder(): QueryHolder {
        return QueryHolder()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideNewsPager(
        newsDatabase: NewsDatabase,
        newsApi: NewsApi,
        queryHolder: QueryHolder,
    ): Pager<Int, NewsItemEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NewsRemoteMediator(
                query = queryHolder.query,
                newsApi = newsApi,
                newsDatabase = newsDatabase
            ),
            pagingSourceFactory = {
                newsDatabase.dao.pagingSource()
            }
        )
    }
}