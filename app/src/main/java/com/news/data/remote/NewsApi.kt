package com.news.data.remote

import com.news.data.entity.News
import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getNews(): News
}