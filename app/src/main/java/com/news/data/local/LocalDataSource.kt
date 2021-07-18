package com.news.data.local

import androidx.lifecycle.LiveData
import com.news.data.entity.Article

interface LocalDataSource {
    fun getAllNews(): LiveData<List<Article>>
    suspend fun insertArticles(articles: List<Article>?)
    suspend fun updateArticle(article: Article)
    fun getFavourites(): LiveData<List<Article>>
}
