package com.news.data.remote

import com.news.BuildConfig
import com.news.data.entity.News
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.API_KEY

interface NewsApi {
    @GET("/v2/top-headlines?apiKey=$API_KEY")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String
    ): News
}