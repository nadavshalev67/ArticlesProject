package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.ServerArticle
import com.work.articles.settings.customkeyvalue.DateKeyValue

class DateFilter(param: DateKeyValue) : FilterBase<DateKeyValue>(param) {
    override fun filter(article: ServerArticle) =
        article.date >= value.value.from && article.date <= value.value.until

    override fun getFilterType() = FilterType.DateFilterType


}
