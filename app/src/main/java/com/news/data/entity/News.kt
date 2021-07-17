package com.news.data.entity

import com.squareup.moshi.Json

data class News(
    @Json(name = "articles")
    val articles: List<Article?>? = null,
)
