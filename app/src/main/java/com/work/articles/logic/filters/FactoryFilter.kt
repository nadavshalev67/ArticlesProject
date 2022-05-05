package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.logic.filters.custom.DateWrapper

class FactoryFilter {

    companion object {
        fun createFilter(filterType: FilterType, value: Any): FilterBase<*>? {
            when (filterType) {
                FilterType.TopicFilterType -> {
                    if (value is String) {
                        return TopicFilter(value)
                    }
                }
                FilterType.DateFilterType -> {
                    if (value is DateWrapper) {
                        return DateFilter(value)
                    }
                }
                FilterType.TitleFilterType -> {
                    if (value is String) {
                        return TitleFilter(value)
                    }
                }
            }
            return null
        }
    }

}