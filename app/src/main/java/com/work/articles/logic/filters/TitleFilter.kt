package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.ServerArticle

class TitleFilter(param: String) : FilterBase<String>(param) {
    override fun filter(article: ServerArticle) = article.title.contains(value)
    override fun getFilterType() = FilterType.TitleFilterType
}
