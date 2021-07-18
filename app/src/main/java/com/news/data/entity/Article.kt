package com.news.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Article")
data class Article(

    @PrimaryKey()
    @Json(name = "title")
    val title: String,

    @Json(name = "publishedAt")
    val publishedAt: String? = null,

    @Json(name = "author")
    val author: String? = null,

    @Json(name = "urlToImage")
    val urlToImage: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "content")
    val content: String? = null,

    var favourite: Boolean?,
    )
