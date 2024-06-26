package com.fridaaltunyan.newsapp.data.remote

data class NewsResponse(
    val fields: Fields,
    val id: String,
    val currentPage: Int,
    val webPublicationDate: String,
    val webTitle: String,
)