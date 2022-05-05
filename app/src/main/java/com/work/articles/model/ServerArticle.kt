package com.work.articles.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerArticle(
    @SerialName("id")
    val id: String,
    @SerialName("source")
    val source: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("article_url")
    val articleUrl: String,
    @SerialName("date")
    val date: Long,
    @SerialName("topicsRanking")
    val topics: Map<String, Float>,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("provider")
    val provider: String,
    @SerialName("view_mode")
    val viewMode: String,
)
