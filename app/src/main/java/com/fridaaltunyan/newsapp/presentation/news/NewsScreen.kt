package com.fridaaltunyan.newsapp.presentation.news

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.fridaaltunyan.newsapp.domain.model.UINews
import com.fridaaltunyan.newsapp.ui.theme.PrimaryBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    news: LazyPagingItems<UINews>,
    viewModel: NewsViewModel,
    onCardClick: (UINews) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = news.loadState) {
        if (news.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (news.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    val searchText by viewModel.searchText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = PrimaryBackground)
            .padding(top = 16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PrimaryBackground,
                unfocusedContainerColor = PrimaryBackground,
                focusedTextColor = Color.White,
            ),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (news.loadState.refresh is LoadState.Loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = news.itemCount,
                    key = news.itemKey { it },
                ) { index ->
                    val item = news[index]
                    if (item != null) {
                        NewsItem(
                            news = UINews(
                                webTitle = item.webTitle,
                                webPublicationDate = item.webPublicationDate,
                                thumbnail = item.thumbnail,
                            ), modifier = Modifier.fillMaxWidth()
                        ) {
                            onCardClick.invoke(
                                UINews(
                                    webTitle = item.webTitle,
                                    webPublicationDate = item.webPublicationDate,
                                    thumbnail = item.thumbnail,
                                )
                            )
                        }
                    }
                }
                item {
                    if (news.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}