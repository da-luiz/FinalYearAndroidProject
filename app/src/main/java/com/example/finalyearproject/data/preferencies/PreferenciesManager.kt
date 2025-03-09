package com.example.finalyearproject.data.preferencies

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class PreferencesManager(private val context: Context) {

    private val THEME_KEY = booleanPreferencesKey("dark_mode")
    private val VIEW_TYPE_KEY = stringPreferencesKey("last_view")

    val themeMode: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: false
    }

    suspend fun saveThemeMode(isDarkMode: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = isDarkMode
        }
    }

    val lastSelectedView: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[VIEW_TYPE_KEY] ?: "week"
    }

    suspend fun saveLastSelectedView(viewType: String) {
        context.dataStore.edit { prefs ->
            prefs[VIEW_TYPE_KEY] = viewType
        }
    }
}
