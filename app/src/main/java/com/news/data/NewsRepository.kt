package com.news.data

import com.news.data.sharedpreferences.SharedPreferencesStorage
import javax.inject.Inject

class NewsRepository
@Inject constructor(private val sharedPreferencesStorage: SharedPreferencesStorage) : Repository {
    override fun isFirstTime(): Boolean {
        return sharedPreferencesStorage.isFirstTime
    }

    override fun saveCountry(country: String) {
        sharedPreferencesStorage.saveCountry(country)
    }

    override fun saveCategories(categories: Array<String?>) {
        sharedPreferencesStorage.saveCategories(categories)
    }
}