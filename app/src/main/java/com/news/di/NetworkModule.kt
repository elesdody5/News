package com.news.di

import com.news.BuildConfig
import com.news.data.remote.NewsApi
import com.news.data.remote.NewsRemoteDataSource
import com.news.data.remote.RemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Movie API communication setup via Retrofit.
 */
private const val API_KEY = BuildConfig.API_KEY
private const val NEWS_BASE_URL = "https://newsapi.org"

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Makes Hilt provide Retrofit instance when a Retrofit type is requested
    @Provides
    @Singleton
    fun providesRetrofit(): NewsApi {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(NewsApi::class.java)
    }
}