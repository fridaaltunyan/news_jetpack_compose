package com.fridaaltunyan.newsapp.data.remote

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    suspend fun getAllNews()
    suspend fun getNewsDetail(id: Long)

    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("api-key") apiKey: String,
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") fields: String = "thumbnail,body",
    ): Flow<PagingData<NewsPaginationResponse>>

    companion object {
        const val BASE_URL = "https://content.guardianapis.com/"
    }
}