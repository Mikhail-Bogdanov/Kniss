package com.repositories.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.qwertyuiop.localdatawhite.dataStore.DataStoreUtils.FIELD_THEME
import com.qwertyuiop.localdatawhite.repository.theme.ThemeRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ThemeRepositoryTest {
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope,
        produceFile = { tmpFolder.newFile("user.preferences_pb") }
    )

    private val expectedTheme = false
    private val expectedThemeKey = FIELD_THEME //exactly the same as in the repo

    private val themeRepository = ThemeRepositoryImpl(testDataStore)

    @Test
    fun saveTheme_changes_saved() = runTest {
        themeRepository.saveTheme(!expectedTheme)

        val actual = testDataStore.data.map {
            it[expectedThemeKey]
        }.first()

        assert(!expectedTheme == actual)
    }

    @Test
    fun getAcceptingRequired_returns_expected() = runTest {
        val actual = themeRepository.getSavedTheme().first()

        assert(expectedTheme == actual)
    }

    @Before
    fun setup() = runTest {
        testDataStore.edit {
            it[expectedThemeKey] = expectedTheme
        }
    }
}