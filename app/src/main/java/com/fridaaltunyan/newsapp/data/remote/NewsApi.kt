package com.fridaaltunyan.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String? = null,
        @Query("api-key") apiKey: String = "test",
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") fields: String = "thumbnail",
    ): NewsPaginationResponse

    companion object {
        const val BASE_URL = "https://content.guardianapis.com/"
    }
}