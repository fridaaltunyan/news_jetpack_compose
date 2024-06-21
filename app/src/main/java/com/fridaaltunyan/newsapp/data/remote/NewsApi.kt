package com.fridaaltunyan.newsapp.data.remote

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET()
    suspend fun getNewsDetail(id: Long): NewsPaginationResponse

    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String? = null,
        @Query("api-key") apiKey: String = "test",
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") fields: String = "thumbnail",
    ): List<NewsPaginationResponse>

    companion object {
        const val BASE_URL = "https://content.guardianapis.com/"
    }
}