package com.fridaaltunyan.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NewsDao {
    @Query("DELETE FROM news")
    suspend fun clearAll()

    @Query("SELECT * FROM news WHERE id = :id")
    suspend fun getArticleById(id: String): NewsItemEntity?

    @Upsert
    suspend fun upsertAll(news: List<NewsItemEntity>)

    @Query("SELECT * FROM news")
    fun pagingSource(): PagingSource<Int, NewsItemEntity>
}