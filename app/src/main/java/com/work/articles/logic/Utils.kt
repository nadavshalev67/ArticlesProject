package com.work.articles.logic

import com.work.articles.logic.filters.FactoryFilter
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.FilterSettings
import com.work.articles.settings.BaseKeyValueCustom
import org.json.JSONObject

object Utils {
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(
        p1: T1?,
        p2: T2?,
        p3: T3?,
        block: (T1, T2, T3) -> R?
    ): R? {
        return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
    }

    fun JSONObject.toSettings(): FilterSettings {
        val settingsBuilder = FilterSettings.Builder()
        this.keys().forEach { key ->
            FilterType.getFilterTypeByKey(key)?.also { filterType ->
                BaseKeyValueCustom.createKeyValueByFilterType(filterType, this.optString(key))
                    .also { value ->
                        FactoryFilter.createFilter(filterType, value)?.let {
                            settingsBuilder.putFilterType(it)
                        }
                    }
            }
        }
        return settingsBuilder.build()
    }

}