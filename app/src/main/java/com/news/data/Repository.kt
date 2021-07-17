package com.news.data

import androidx.lifecycle.LiveData
import com.news.data.entity.News

interface Repository {
    fun isFirstTime(): Boolean
    fun saveCountry(country: String)
    fun saveCategories(categories: Array<String?>)
    fun getNewsList(): LiveData<List<News>>
}