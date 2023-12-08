package com.qwertyuiop.localdatagray.dataStore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreUtils {

    private const val DATA_STORE_NAME = "DATA_STORE_NAME"

    val Context.dataStore by preferencesDataStore(
        name = DATA_STORE_NAME
    )

    private const val URL = "URL"

    val FIELD_URL = stringPreferencesKey(URL)
}