package com.work.articles.logic.filters.base

import com.work.articles.settings.BaseKeyValueCustom


sealed class FilterType(val name: String) {
    object TopicFilterType : FilterType("Topic")
    object TitleFilterType : FilterType("Title")
    object DateFilterType : FilterType("DateFilter")


    companion object {
        fun getFilterTypeByKey(key: String): FilterType? {
            when (key) {
                TopicFilterType.name -> return TopicFilterType
                TitleFilterType.name -> return TitleFilterType
                DateFilterType.name -> return DateFilterType
            }
            return null
        }
    }
}

abstract class FilterBase<T : BaseKeyValueCustom<*>>(val value: T) : FilterArticle {
    abstract fun getFilterType(): FilterType
}