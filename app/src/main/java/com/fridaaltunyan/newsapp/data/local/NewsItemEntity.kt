package com.fridaaltunyan.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsItemEntity(
    @PrimaryKey val id: String,
    val webTitle: String,
    val webPublicationDate: String,
    val thumbnail: String?,
)