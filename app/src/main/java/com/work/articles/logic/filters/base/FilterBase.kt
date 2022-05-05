package com.work.articles.logic.filters.base

import com.work.articles.logic.filters.custom.DateWrapper


sealed class FilterType(val name: String) {
    object TopicFilterType : FilterType("Topic")
    object TitleFilterType : FilterType("Title")
    object DateFilterType : FilterType("DateFilter")

    companion object {
        fun retriveCacheFilterValue(key: FilterType, value: String): Any? {
            return when (key) {
                TopicFilterType, TitleFilterType -> value
                DateFilterType -> DateWrapper.fromString(value)
            }
            return null
        }

        fun retriveCacheFilterKey(key: String): FilterType? {
            when (key) {
                TopicFilterType.name -> return TopicFilterType
                TitleFilterType.name -> return TitleFilterType
                DateFilterType.name -> return DateFilterType
            }
            return null
        }


    }


}

abstract class FilterBase<T>(val value: T) : FilterArticle<T> {
    abstract fun getFilterType(): FilterType
}