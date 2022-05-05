package com.work.articles.repository

import com.work.articles.api.ArticlesApiImpl
import com.work.articles.model.FilterSettings
import javax.inject.Inject


class ArticlesRepository @Inject constructor(
    private val apiHelper: ArticlesApiImpl,
    private val cacheSettings: CacheSettings
) {

    suspend fun saveSettings(settings: FilterSettings) =
        cacheSettings.saveSettings(settings.toJsonObject().toString())

    suspend fun retrieveSettings() = cacheSettings.getSettings()


    suspend fun fetchData() = apiHelper.getArticles()
}