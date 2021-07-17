package com.news.di

import android.content.Context
import android.content.SharedPreferences
import com.news.data.sharedpreferences.SharedPreferencesStorage
import com.news.data.sharedpreferences.SharedPreferencesStorageImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "News",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun bindSharedPreferenceStorage(sharedPreferences: SharedPreferences): SharedPreferencesStorage {
        return SharedPreferencesStorageImp(sharedPreferences)
    }
}