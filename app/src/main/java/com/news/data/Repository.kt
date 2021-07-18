package com.news.data

import androidx.lifecycle.LiveData
import com.news.data.entity.Article

interface Repository {
    fun isFirstTime(): Boolean
    fun saveCountry(country: String)
    fun saveCategories(categories: Array<String?>)
    fun getNewsList(): LiveData<List<Article>>
    suspend fun refreshList()
    suspend fun  updateArticle(article: Article)
}