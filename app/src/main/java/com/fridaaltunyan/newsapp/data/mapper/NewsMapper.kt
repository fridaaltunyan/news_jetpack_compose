package com.fridaaltunyan.newsapp.data.mapper

import com.fridaaltunyan.newsapp.data.local.NewsItemEntity
import com.fridaaltunyan.newsapp.data.remote.NewsResponse
import com.fridaaltunyan.newsapp.domain.model.UINews

fun NewsItemEntity.toUINews(): UINews = UINews(
    webTitle = webTitle,
    webPublicationDate = webPublicationDate,
    thumbnail = thumbnail,
)

fun NewsResponse.toNewsItemEntity(): NewsItemEntity = NewsItemEntity(
    id = id,
    currentPage = currentPage,
    webTitle = webTitle,
    webPublicationDate = webPublicationDate,
    thumbnail = fields.thumbnail,
)
