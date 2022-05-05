package com.work.articles.di

import android.content.Context
import com.work.articles.BaseApplication
import com.work.articles.api.ArticleService
import com.work.articles.api.ArticlesApiHelper
import com.work.articles.api.ArticlesApiImpl
import com.work.articles.repository.CacheSettings
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    fun provideBaseUrl() =
        "https://gist.githubusercontent.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ArticleService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ArticlesApiImpl): ArticlesApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideCacheSettings(context: BaseApplication) = CacheSettings(context)

}