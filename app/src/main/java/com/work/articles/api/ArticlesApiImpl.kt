package com.work.articles.api

import javax.inject.Inject

class ArticlesApiImpl @Inject constructor(
    private val apiService: ArticleService
) : ArticlesApiHelper {
    override suspend fun getArticles() = apiService.getArticles()
}

