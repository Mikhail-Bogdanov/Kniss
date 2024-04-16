package com.evoteam.localData.repository.tutorial

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.evoteam.domain.repositories.tutorial.TutorialRepository
import com.evoteam.localData.dataStore.DataStoreUtils.FIELD_TUTORIAL
import com.evoteam.localData.utils.UtilsConstants.DEFAULT_TUTORIAL_ENDED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TutorialRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : TutorialRepository {
    override suspend fun getTutorialEnded() = withContext(Dispatchers.IO) {
        dataStore.data.map { prefs ->
            prefs[FIELD_TUTORIAL] ?: DEFAULT_TUTORIAL_ENDED
        }
    }.first()

    override suspend fun setTutorialEnded(): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { prefs ->
            prefs[FIELD_TUTORIAL] = true
        }
    }
}