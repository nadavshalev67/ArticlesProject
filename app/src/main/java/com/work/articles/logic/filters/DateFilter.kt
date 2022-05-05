package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.logic.filters.custom.DateWrapper
import com.work.articles.model.ServerArticle

class DateFilter(param: DateWrapper) : FilterBase<DateWrapper>(param) {
    override fun filter(article: ServerArticle) =
        article.date >= value.from && article.date <= value.until

    override fun getFilterType() = FilterType.DateFilterType


}
