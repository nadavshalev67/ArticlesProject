package com.work.articles.api

import com.work.articles.model.ServerArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleService {
    @GET("alonaviram/1184df2468f5968a4319c9e50e3dac98/raw/e208550c3862fcd2eb4d61d1be958b900cdd4afc/artickes")
    suspend fun getArticles(): Response<ServerArticlesResponse>
}