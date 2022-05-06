package com.work.articles.settings.customkeyvalue

import com.work.articles.logic.filters.base.FilterType
import com.work.articles.settings.BaseKeyValueCustom

class StringKeyValue(key: FilterType, value: String) : BaseKeyValueCustom<String>(key, value) {

    override fun parseValueFromString(value: String) = value

    override fun valueToString() = value
}