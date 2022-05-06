package com.work.articles.settings.customkeyvalue

import com.work.articles.logic.filters.base.FilterType
import com.work.articles.settings.BaseKeyValueCustom

class DateKeyValue : BaseKeyValueCustom<DateWrapper> {
    constructor(key: FilterType, value: String) : super(key, value)
    constructor(key: FilterType, value: DateWrapper) : super(key, value)

    override fun parseValueFromString(value: String): DateWrapper {
        return try {
            val date = value.split(",")
            DateWrapper(date[0].toLong(), date[1].toLong())
        } catch (ex: Exception) {
            DateWrapper(0, 0)
        }
    }

    override fun valueToString() = "${value.from},${value.until}"
}

data class DateWrapper(val from: Long, val until: Long)