package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.ServerArticle
import com.work.articles.settings.customkeyvalue.StringKeyValue

class TopicFilter(param: StringKeyValue) : FilterBase<StringKeyValue>(param) {
    override fun filter(article: ServerArticle) = article.topics.keys.contains(value.value)
    override fun getFilterType() = FilterType.TopicFilterType
}
