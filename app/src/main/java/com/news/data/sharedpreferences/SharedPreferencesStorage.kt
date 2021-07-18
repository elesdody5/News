package com.news.data.sharedpreferences

interface SharedPreferencesStorage {
    val isFirstTime: Boolean
    fun saveCountry(country: String)
    fun saveCategories(categories: Array<String?>)
    fun getCountry(): String?
    fun getCategories(): Set<String>?
}
