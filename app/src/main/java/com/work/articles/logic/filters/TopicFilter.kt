package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.ServerArticle

class TopicFilter(param: String) : FilterBase<String>(param) {
    override fun filter(article: ServerArticle) = article.topics.keys.contains(value)
    override fun getFilterType() = FilterType.TopicFilterType
}
