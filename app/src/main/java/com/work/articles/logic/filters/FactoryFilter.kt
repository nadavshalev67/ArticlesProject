package com.work.articles.logic.filters

import com.work.articles.logic.filters.base.FilterBase
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.settings.BaseKeyValueCustom
import com.work.articles.settings.customkeyvalue.DateKeyValue
import com.work.articles.settings.customkeyvalue.StringKeyValue

class FactoryFilter {

    companion object {
        fun createFilter(filterType: FilterType, value: BaseKeyValueCustom<*>): FilterBase<*>? {
            if (filterType is FilterType.TopicFilterType && value is StringKeyValue) {
                return TopicFilter(value)
            } else if (filterType is FilterType.DateFilterType && value is DateKeyValue) {
                return DateFilter(value)
            } else if (filterType is FilterType.TitleFilterType && value is StringKeyValue) {
                return TitleFilter(value)
            }
            return null
        }
    }
}