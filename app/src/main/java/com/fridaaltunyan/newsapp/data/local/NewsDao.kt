package com.fridaaltunyan.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: NewsItemEntity)

    @Query("SELECT * FROM news WHERE id = :id")
    suspend fun getArticleById(id: String): NewsItemEntity?

    @Query("SELECT * FROM news")
    suspend fun getAllArticles(): List<NewsItemEntity>
}