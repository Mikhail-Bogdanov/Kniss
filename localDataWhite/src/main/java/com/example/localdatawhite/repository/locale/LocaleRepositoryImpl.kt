package com.example.localdatawhite.repository.locale

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.domainwhite.repositories.locale.LocaleRepository
import com.example.localdatawhite.dataStore.DataStoreUtils.FIELD_LOCALE
import com.example.localdatawhite.utils.UtilsConstants.DEFAULT_LOCALE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LocaleRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : LocaleRepository {

    override fun getSavedLocale(): Flow<String> = dataStore.data.map { store ->
        store[FIELD_LOCALE] ?: DEFAULT_LOCALE
    }.flowOn(Dispatchers.IO)

    override suspend fun saveLocale(locale: String) {
        dataStore.edit { store ->
            store[FIELD_LOCALE] = locale
        }
    }
}