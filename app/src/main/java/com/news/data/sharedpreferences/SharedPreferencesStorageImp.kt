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
            CATEGORIES_KEY, null
        ) != null

    override fun saveCountry(country: String) {
        sharedPreferences.edit(commit = true) { putString(COUNTRY_KEY, country) }
    }

    override fun saveCategories(categories: Array<String?>) {
        sharedPreferences.edit(commit = true) {
            putStringSet(
                CATEGORIES_KEY,
                categories.toHashSet()
            )
        }
    }
}