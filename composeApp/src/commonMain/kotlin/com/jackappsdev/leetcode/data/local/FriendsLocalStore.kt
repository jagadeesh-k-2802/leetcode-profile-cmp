package com.jackappsdev.leetcode.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jackappsdev.leetcode.domain.model.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class FriendsLocalStore(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
) {
    private val FRIENDS_KEY = stringPreferencesKey("friends_list")

    val friends: Flow<List<Friend>> = dataStore.data.map { prefs ->
        prefs[FRIENDS_KEY]?.let { stored ->
            try {
                json.decodeFromString<List<Friend>>(stored)
            } catch (_: Throwable) {
                emptyList()
            }
        } ?: emptyList()
    }

    suspend fun save(list: List<Friend>) {
        dataStore.edit { prefs ->
            prefs[FRIENDS_KEY] = json.encodeToString(list)
        }
    }
}
