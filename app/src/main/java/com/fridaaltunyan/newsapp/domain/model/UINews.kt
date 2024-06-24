package com.fridaaltunyan.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UINews(
    val webTitle: String,
    val webPublicationDate: String,
    val thumbnail: String?,
): Parcelable