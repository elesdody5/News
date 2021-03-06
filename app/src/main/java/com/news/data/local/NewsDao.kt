/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.news.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.news.data.entity.Article

/**
 * Data Access Object for the news table.
 */
@Dao
interface NewsDao {

    /**
     * Select all articles from the news table.
     *
     * @return all articles.
     */
    @Query("SELECT * FROM Article WHERE category == :selectedCategory ")
    fun getArticles(selectedCategory: String?): LiveData<List<Article>>

    @Query("SELECT * FROM Article")
    fun getArticles(): LiveData<List<Article>>


    /**
     * Insert a article in the database. If the article already exists, replace it.
     *
     * @param article the article to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articles: List<Article>)

    /**
     * Update a article.
     *
     * @param article article to be updated
     * @return the number of news updated. This should always be 1.
     */
    @Update
    suspend fun updateArticle(article: Article): Int

    @Query("SELECT * FROM Article WHERE favourite == :isFavourite")
    fun getFavouritesArticles(isFavourite: Boolean = true): LiveData<List<Article>>

}
