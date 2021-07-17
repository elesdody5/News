package com.news.data.remote

import com.news.data.entity.News

interface RemoteDataSource {
    suspend fun getNews(country: String, category: String): News
}