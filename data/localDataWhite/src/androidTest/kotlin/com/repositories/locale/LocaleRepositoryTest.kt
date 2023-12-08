package com.repositories.locale

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.qwertyuiop.localdatawhite.dataStore.DataStoreUtils.FIELD_LOCALE
import com.qwertyuiop.localdatawhite.repository.locale.LocaleRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LocaleRepositoryTest {
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope,
        produceFile = { tmpFolder.newFile("user.preferences_pb") }
    )

    private val expectedLocale = "en"
    private val expectedLocaleKey = FIELD_LOCALE //exactly the same as in the repo

    private val localeRepository = LocaleRepositoryImpl(testDataStore)

    @Test
    fun getSavedLocale_returns_expected() = runTest {
        val actual = localeRepository.getSavedLocale().first()

        assert(expectedLocale == actual)
    }

    @Test
    fun saveLocale_changes_saved_locale() = runTest {
        localeRepository.saveLocale(expectedLocale.plus("test")) //plus anything

        val actual = localeRepository.getSavedLocale().first()

        assert(expectedLocale != actual)
    }

    @Before
    fun setup() = runTest {
        testDataStore.edit {
            it[expectedLocaleKey] = expectedLocale
        }
    }
}