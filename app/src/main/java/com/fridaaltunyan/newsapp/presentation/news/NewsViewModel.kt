package com.fridaaltunyan.newsapp.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fridaaltunyan.newsapp.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val newsPagingFlow = _searchText
        .debounce(600)
        .flatMapLatest {
            newsRepository.getNews(if (it.isEmpty()) null else it)
        }.cachedIn(viewModelScope)

    @OptIn(FlowPreview::class)
    fun onSearchTextChange(text: String) {
        viewModelScope.launch {
            _searchText.value = text
        }
    }
}