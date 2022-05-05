package com.work.articles.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val DATASTORE_ARTICLES_NAME = "articles"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_ARTICLES_NAME)

class CacheSettings(val context: Context) {

    companion object {
        val SETTINGS_KEY = stringPreferencesKey("settings")
    }

    suspend fun saveSettings(settings: String) {
        context.datastore.edit { prefs ->
            prefs[SETTINGS_KEY] = settings
        }
    }

    suspend fun getSettings() = context.datastore.data.map { prefs ->
        prefs[SETTINGS_KEY]
    }
}