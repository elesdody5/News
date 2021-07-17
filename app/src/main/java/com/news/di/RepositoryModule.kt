package com.news.di

import com.news.data.NewsRepository
import com.news.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(newsRepository: NewsRepository): Repository
}