package com.qwertyuiop.localdatagray.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.qwertyuiop.domaingray.repositories.local.LocalGrayRepository
import com.qwertyuiop.localdatagray.dataStore.DataStoreUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalGrayRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : LocalGrayRepository {

    override fun getSavedUrl(): Flow<String?> = dataStore.data.map { store ->
        store[DataStoreUtils.FIELD_URL]
    }

    override suspend fun saveUrl(url: String) {
        dataStore.edit { store ->
            store[DataStoreUtils.FIELD_URL] = url
        }
    }
}