package com.news.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.news.data.entity.Article
import com.news.data.local.LocalDataSource
import com.news.data.remote.RemoteDataSource
import com.news.data.sharedpreferences.SharedPreferencesStorage
import javax.inject.Inject

class NewsRepository
@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDatasource: LocalDataSource,
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : Repository {
    override fun isFirstTime(): Boolean {
        return sharedPreferencesStorage.isFirstTime
    }

    override fun saveCountry(country: String) {
        sharedPreferencesStorage.saveCountry(country)
    }

    override fun saveCategories(categories: Array<String?>) {
        sharedPreferencesStorage.saveCategories(categories)
    }

    override fun getNewsList(): LiveData<List<Article>> {
        return localDatasource.getAllNews()
    }

    override suspend fun refreshList(category: String?) {
        val country = sharedPreferencesStorage.getCountry()
        val categories = sharedPreferencesStorage.getCategories()
        val selectedCategory = category ?: categories?.elementAt(0) ?: ""
        if (country != null && categories != null) {
            try {
                val news = remoteDataSource.getNews(country, selectedCategory)
                news.articles?.forEach { article -> article.category = selectedCategory }
                localDatasource.insertArticles(news.articles)
            } catch (e: Exception) {
                Log.e("NewsRepository", "No internetConnection ")
            }
        }

    }

    override suspend fun updateArticle(article: Article) {
        localDatasource.updateArticle(article)
    }

    override fun getFavourites(): LiveData<List<Article>> {
        return localDatasource.getFavourites()
    }

    override fun getCategories(): Set<String>? {
        return sharedPreferencesStorage.getCategories()
    }

}