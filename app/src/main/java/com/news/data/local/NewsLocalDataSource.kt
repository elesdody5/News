package com.news.data.local

import androidx.lifecycle.LiveData
import com.news.data.entity.Article
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) : LocalDataSource {
    override fun getAllNews(): LiveData<List<Article>> {
        return newsDao.getArticles()
    }
}