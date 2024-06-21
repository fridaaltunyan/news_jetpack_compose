package com.fridaaltunyan.newsapp.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fridaaltunyan.newsapp.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {
    val newsPagingFlow = newsRepository.getPokemonList()
        .cachedIn(viewModelScope)
}