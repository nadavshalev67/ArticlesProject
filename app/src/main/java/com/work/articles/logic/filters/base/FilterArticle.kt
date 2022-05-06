package com.work.articles.logic.filters.base

import com.work.articles.model.ServerArticle

interface FilterArticle {
    fun filter(article: ServerArticle): Boolean
}
