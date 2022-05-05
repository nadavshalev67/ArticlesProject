package com.work.articles.api

import com.work.articles.model.ServerArticlesResponse

import retrofit2.Response

interface ArticlesApiHelper {
    suspend fun getArticles(): Response<ServerArticlesResponse>
}