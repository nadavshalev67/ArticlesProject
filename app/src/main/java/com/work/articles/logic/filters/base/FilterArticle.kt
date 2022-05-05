package com.work.articles.logic.filters.base

import com.work.articles.model.ServerArticle

interface FilterArticle<T> {
    fun filter(article: ServerArticle): Boolean
}
