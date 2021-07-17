package com.news.data.local

import androidx.lifecycle.LiveData
import com.news.data.entity.Article

interface LocalDataSource {
    fun getAllNews(): LiveData<List<Article>>
}
