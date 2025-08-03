package com.jackappsdev.leetcode.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.java.KoinJavaComponent.getKoin

private val Context.dataStore by preferencesDataStore(name = "settings")

actual class DataStoreFactory {
    actual fun getDataStore(): DataStore<Preferences> {
        val context = getKoin().get<Context>()
        return context.dataStore
    }
}
