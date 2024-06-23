package com.fridaaltunyan.newsapp.domain.repo

import androidx.paging.PagingData
import com.fridaaltunyan.newsapp.domain.model.UINews
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<PagingData<UINews>>
}