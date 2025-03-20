package com.example.finalyearproject.data.preferencies

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// ✅ Define DataStore Extension for Context
private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class PreferencesManager(private val context: Context) {

    companion object {
        private val KEY_USERNAME = stringPreferencesKey("key_username")
        private val KEY_NOTIFICATIONS = booleanPreferencesKey("key_notifications")
    }

    // ✅ Save username
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
        }
    }

    // ✅ Get username
    val usernameFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[KEY_USERNAME] }

    // ✅ Enable/Disable Notifications
    suspend fun saveNotificationPreference(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_NOTIFICATIONS] = enabled
        }
    }

    val notificationsFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[KEY_NOTIFICATIONS] ?: true }

    // ✅ Get Value Synchronously (For Testing)
    fun getSavedUsername(): String? = runBlocking {
        context.dataStore.data.map { it[KEY_USERNAME] }.first()
    }
}
