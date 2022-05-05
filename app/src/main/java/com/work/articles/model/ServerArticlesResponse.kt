package com.work.articles.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerArticlesResponse(
    @SerialName("articles")
    val articles: List<ServerArticle>
)