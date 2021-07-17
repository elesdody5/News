package com.news.di

import android.content.Context
import android.content.SharedPreferences
import com.news.data.sharedpreferences.SharedPreferencesStorage
import dagger.Binds
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "News",
            Context.MODE_PRIVATE
        )
    }

    @Binds
    @Singleton
    abstract fun bindSharedPreferenceStorage(sharedPreferencesStorage: SharedPreferencesStorage): SharedPreferencesStorage
}