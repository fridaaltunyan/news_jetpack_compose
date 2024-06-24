package com.fridaaltunyan.newsapp.presentation.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fridaaltunyan.newsapp.domain.model.UINews
import com.fridaaltunyan.newsapp.ui.theme.CardPrimaryBackground
import com.fridaaltunyan.newsapp.ui.theme.Gray300
import com.fridaaltunyan.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsItem(
    news: UINews,
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardPrimaryBackground
        ),
        onClick = onCardClicked
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = news.thumbnail,
                contentDescription = news.webTitle,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = news.webTitle,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.webPublicationDate,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    color = Gray300,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    NewsAppTheme {
        NewsItem(
            news = UINews(
                webTitle = "sagittis", webPublicationDate = "ligula", thumbnail = null
            ),
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}

