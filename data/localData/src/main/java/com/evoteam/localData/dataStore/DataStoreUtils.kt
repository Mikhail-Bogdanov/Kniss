package com.evoteam.localData.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreUtils {

    private const val DATA_STORE_NAME = "DATA_STORE_NAME"

    val Context.dataStore by preferencesDataStore(
        name = DATA_STORE_NAME
    )

    private const val THEME = "THEME"
    private const val TUTORIAL = "TUTORIAL"

    val FIELD_THEME = booleanPreferencesKey(THEME)
    val FIELD_TUTORIAL = booleanPreferencesKey(TUTORIAL)
}