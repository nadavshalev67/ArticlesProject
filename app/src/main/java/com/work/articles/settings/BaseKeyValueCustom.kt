package com.work.articles.settings

import com.work.articles.logic.filters.base.FilterType
import com.work.articles.settings.customkeyvalue.DateKeyValue
import com.work.articles.settings.customkeyvalue.StringKeyValue

abstract class BaseKeyValueCustom<T> {
    var value: T
    var key: String

    constructor(key: FilterType, value: String) {
        this.key = key.name
        this.value = parseValueFromString(value)
    }

    constructor(key: FilterType, value: T) {
        this.key = key.name
        this.value = value
    }


    abstract fun parseValueFromString(value: String): T
    abstract fun valueToString(): String

    companion object {
        fun createKeyValueByFilterType(key: FilterType, value: String): BaseKeyValueCustom<*> {
            return when (key) {
                FilterType.TopicFilterType, FilterType.TitleFilterType -> StringKeyValue(key, value)
                FilterType.DateFilterType -> DateKeyValue(key, value)
            }
        }
    }
}










