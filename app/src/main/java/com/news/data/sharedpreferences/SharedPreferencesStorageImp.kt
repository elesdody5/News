package com.news.data.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.news.utils.CATEGORIES_KEY
import com.news.utils.COUNTRY_KEY
import javax.inject.Inject

class SharedPreferencesStorageImp @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedPreferencesStorage {

    override val isFirstTime: Boolean
        get() = sharedPreferences.getString(
            COUNTRY_KEY,
            null
        ) != null && sharedPreferences.getString(
            CATEGORIES_KEY, null
        ) != null

    override fun saveCountry(country: String) {
        sharedPreferences.edit { putString(COUNTRY_KEY, country) }
    }

    override fun saveCategories(categories: Array<String?>) {
        sharedPreferences.edit { putStringSet(CATEGORIES_KEY, categories.toHashSet()) }
    }
}