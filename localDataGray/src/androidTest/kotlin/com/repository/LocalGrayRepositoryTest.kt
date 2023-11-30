package com.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.qwertyuiop.localdatagray.dataStore.DataStoreUtils
import com.qwertyuiop.localdatagray.repository.LocalGrayRepositoryImpl
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

class LocalGrayRepositoryTest {
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope,
        produceFile = { tmpFolder.newFile("user.preferences_pb") }
    )

    private val expectedUrl = "www.com.ru"
    private val expectedUrlKey = DataStoreUtils.FIELD_URL //exactly the same as in the repo

    private val localGrayRepository = LocalGrayRepositoryImpl(testDataStore)

    @Test
    fun getSavedUrl_returns_expected() = runTest {
        val actual = localGrayRepository.getSavedUrl().first()

        assert(expectedUrl == actual)
    }

    @Test
    fun saveUrl_changes_saved_url() = runTest {
        val newExpected = expectedUrl.plus(System.currentTimeMillis())

        localGrayRepository.saveUrl(newExpected)

        val actual = localGrayRepository.getSavedUrl().first()

        assert(newExpected == actual)
    }

    @Before
    fun setup() = runTest {
        testDataStore.edit {
            it[expectedUrlKey] = expectedUrl
        }
    }
}