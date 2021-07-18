package com.news.data.local

import androidx.lifecycle.LiveData
import com.news.data.entity.Article
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) : LocalDataSource {
    override fun getAllNews(category: String?): LiveData<List<Article>> {
        return newsDao.getArticles(category)
    }

    override fun getAllNews(): LiveData<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun insertArticles(articles: List<Article>?) {
        articles?.let {
            newsDao.insertArticle(it)
        }
    }

    override suspend fun updateArticle(article: Article) {
        newsDao.updateArticle(article)
    }

    override fun getFavourites(): LiveData<List<Article>> {
        return newsDao.getFavouritesArticles()
    }
}