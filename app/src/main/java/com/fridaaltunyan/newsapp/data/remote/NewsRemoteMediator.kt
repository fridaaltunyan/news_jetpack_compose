package com.fridaaltunyan.newsapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fridaaltunyan.newsapp.data.local.NewsDatabase
import com.fridaaltunyan.newsapp.data.local.NewsItemEntity
import com.fridaaltunyan.newsapp.data.mapper.toNewsItemEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase,
    private val query: String?,
) : RemoteMediator<Int, NewsItemEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsItemEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.currentPage / state.config.pageSize) + 1
                    }
                }
            }

            val news = newsApi.searchNews(
                query = query,
                page = loadKey,
                pageSize = state.config.pageSize
            )

            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDatabase.dao.clearAll()
                }
                val newsEntities =
                    news.response.newsResponses.map { news -> news.toNewsItemEntity() }
                newsDatabase.dao.upsertAll(newsEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = news.response.pageSize == 20
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}