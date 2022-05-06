package com.work.articles.model

import com.work.articles.logic.filters.base.FilterBase
import org.json.JSONObject


class FilterSettings private constructor(val filters: MutableList<FilterBase<*>> = mutableListOf()) {
    data class Builder(
        private val filters: MutableList<FilterBase<*>> = mutableListOf()
    ) {
        fun putFilterType(filter: FilterBase<*>) = apply { this.filters.add(filter) }
        fun build() = FilterSettings(filters)
    }

    fun toJsonObject(): JSONObject {
        val result = JSONObject()
        filters.forEach {
            result.put(it.value.key, it.value.valueToString())
        }
        return result
    }

    fun applyFilters(article: ServerArticle): Boolean {
        filters.forEach {
            if (!it.filter(article)) {
                return false
            }
        }
        return true
    }
}
