package com.fridaaltunyan.newsapp.data.remote

data class Response(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    val newsResponses: List<NewsResponse>,
    val startIndex: Int,
    val total: Int,
)