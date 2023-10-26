package com.example.localdatawhite.repository.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.domainwhite.repositories.theme.ThemeRepository
import com.example.localdatawhite.dataStore.DataStoreUtils.FIELD_THEME
import com.example.localdatawhite.utils.UtilsConstants.DEFAULT_DARK_THEME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {

    override fun getSavedTheme(): Flow<Boolean> = dataStore.data.map { store ->
        store[FIELD_THEME] ?: DEFAULT_DARK_THEME
    }.flowOn(Dispatchers.IO)

    override suspend fun saveTheme(darkTheme: Boolean) {
        dataStore.edit { store ->
            store[FIELD_THEME] = darkTheme
        }
    }
}