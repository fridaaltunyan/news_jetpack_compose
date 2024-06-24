package com.fridaaltunyan.newsapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.fridaaltunyan.newsapp.domain.model.UINews
import com.fridaaltunyan.newsapp.presentation.navigation.NewsListScreen
import com.fridaaltunyan.newsapp.presentation.news.NewsScreen
import com.fridaaltunyan.newsapp.presentation.news.NewsViewModel
import com.fridaaltunyan.newsapp.presentation.news_detail.NewsDetailItemScreen
import com.fridaaltunyan.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window? = window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.navigationBarColor = ContextCompat.getColor(this, R.color.primary_background)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NewsListScreen
                ) {
                    composable<NewsListScreen> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val viewModel = hiltViewModel<NewsViewModel>()
                            val news = viewModel.newsPagingFlow.collectAsLazyPagingItems()
                            NewsScreen(news = news, viewModel) {
                                navController.navigate(it)
                            }
                        }
                    }
                    composable<UINews> {
                        val args = it.toRoute<UINews>()
                        NewsDetailItemScreen(news = args)
                    }
                }

            }
        }
    }
}


