package com.example.localdatawhite.repository.accepting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.domainwhite.repositories.accepting.AcceptingRepository
import com.example.localdatawhite.dataStore.DataStoreUtils.FIELD_ACCEPTING
import com.example.localdatawhite.utils.UtilsConstants.DEFAULT_ACCEPTING_REQUIRED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AcceptingRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : AcceptingRepository {

    override fun getAcceptingRequired() = dataStore.data.map { store ->
        store[FIELD_ACCEPTING] ?: DEFAULT_ACCEPTING_REQUIRED
    }.flowOn(Dispatchers.IO)

    override suspend fun setAccepted() {
        withContext(Dispatchers.IO) {
            dataStore.edit { store ->
                store[FIELD_ACCEPTING] = false
            }
        }
    }
}