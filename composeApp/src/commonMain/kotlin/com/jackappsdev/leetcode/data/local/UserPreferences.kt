package com.jackappsdev.leetcode.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class UserPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val USERNAME = stringPreferencesKey("username")
    }

    val username: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.USERNAME]
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USERNAME] = username
        }
    }

    suspend fun clearUsername() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.USERNAME)
        }
    }
}