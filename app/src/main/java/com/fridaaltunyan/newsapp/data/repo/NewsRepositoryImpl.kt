package com.fridaaltunyan.newsapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.fridaaltunyan.newsapp.data.local.NewsItemEntity
import com.fridaaltunyan.newsapp.data.mapper.toUINews
import com.fridaaltunyan.newsapp.domain.model.UINews
import com.fridaaltunyan.newsapp.domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsPager: Pager<Int, NewsItemEntity>
) : NewsRepository {
    override fun getPokemonList(): Flow<PagingData<UINews>> {
        return newsPager.flow.map { pagingData ->
            pagingData.map { it.toUINews() }
        }
    }
}