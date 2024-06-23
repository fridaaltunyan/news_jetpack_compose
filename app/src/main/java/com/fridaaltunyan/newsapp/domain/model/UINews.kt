package com.fridaaltunyan.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UINews(
    val webTitle: String,
    val webPublicationDate: String,
    val thumbnail: String?,
):Parcelable