package com.fridaaltunyan.newsapp.presentation.news_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fridaaltunyan.newsapp.domain.model.UINews
import com.fridaaltunyan.newsapp.ui.theme.Gray300
import com.fridaaltunyan.newsapp.ui.theme.PrimaryBackground

@Composable
fun NewsDetailItemScreen(
    news: UINews,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = PrimaryBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = news.thumbnail,
            contentDescription = news.webTitle,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = news.webPublicationDate,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            color = Gray300,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = news.webTitle,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

    }

}
