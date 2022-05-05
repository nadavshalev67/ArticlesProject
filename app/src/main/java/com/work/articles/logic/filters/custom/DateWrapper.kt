package com.work.articles.logic.filters.custom

import java.lang.Exception

data class DateWrapper(val from: Long, val until: Long) {
    override fun toString(): String {
        return "$from,$until"
    }

    companion object {
        fun fromString(string: String): DateWrapper? {
            return try {
                val date = string.split(",")
                DateWrapper(date[0].toLong(), date[1].toLong())
            } catch (ex: Exception) {
                null
            }
        }
    }
}

