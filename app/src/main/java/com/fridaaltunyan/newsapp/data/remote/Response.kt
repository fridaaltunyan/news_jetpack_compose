package com.fridaaltunyan.newsapp.data.remote

import com.google.gson.annotations.SerializedName

data class Response(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    @SerializedName("results")
    val newsResponses: List<NewsResponse>,
    val startIndex: Int,
    val total: Int,
)