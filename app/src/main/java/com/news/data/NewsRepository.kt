package com.news.data

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

    override suspend fun refreshList() {
        val country = sharedPreferencesStorage.getCountry()
        val categories = sharedPreferencesStorage.getCategories()

        if (country != null && categories != null) {
            val news = remoteDataSource.getNews(country, categories.elementAt(0))
            localDatasource.insertArticles(news.articles)
        }

    }

    override suspend fun updateArticle(article: Article) {
        localDatasource.updateArticle(article)
    }

    override fun getFavourites(): LiveData<List<Article>> {
        return localDatasource.getFavourites()
    }

}