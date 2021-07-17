package com.news.di

import com.news.data.local.LocalDataSource
import com.news.data.local.NewsDatabase
import com.news.data.local.NewsLocalDataSource
import com.news.data.remote.NewsRemoteDataSource
import com.news.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModuleBind {

    @Binds
    abstract fun bindRemoteDataSource(newsRemoteDataSource: NewsRemoteDataSource): RemoteDataSource
}

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
class DataSourceModuleProvides {

    @Provides
    fun provideLocalDataSource(newsDatabase: NewsDatabase): LocalDataSource {
        return NewsLocalDataSource(newsDao = newsDatabase.newsDao())
    }
}