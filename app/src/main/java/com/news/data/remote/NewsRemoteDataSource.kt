package com.news.data.remote

import com.news.data.entity.News
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val newsApi: NewsApi) : RemoteDataSource {
    override suspend fun getNews(country: String, category: String): News {
        return newsApi.getNews()
    }
}