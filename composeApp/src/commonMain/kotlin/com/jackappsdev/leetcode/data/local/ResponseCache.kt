package com.jackappsdev.leetcode.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Single
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Single
class ResponseCache(
    private val dataStore: DataStore<Preferences>
) {
    private fun valueKey(url: String) = stringPreferencesKey("cache_value_" + url.hashCode())
    private fun timeKey(url: String) = longPreferencesKey("cache_time_" + url.hashCode())

    suspend fun get(url: String, ttlMillis: Long): String? {
        val prefs = dataStore.data.first()
        val savedAt = prefs[timeKey(url)] ?: return null
        val now = currentTimeMillis()
        if (now - savedAt > ttlMillis) return null
        return prefs[valueKey(url)]
    }

    suspend fun put(url: String, body: String) {
        val now = currentTimeMillis()
        dataStore.edit { prefs ->
            prefs[valueKey(url)] = body
            prefs[timeKey(url)] = now
        }
    }

    // helper for testing or substitution
    @OptIn(ExperimentalTime::class)
    internal fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()
}
