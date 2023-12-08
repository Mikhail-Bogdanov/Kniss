package com.repositories.accepting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.qwertyuiop.localdatawhite.dataStore.DataStoreUtils.FIELD_ACCEPTING
import com.qwertyuiop.localdatawhite.repository.accepting.AcceptingRepositoryImpl
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

class AcceptingRepositoryTest {
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope,
        produceFile = { tmpFolder.newFile("user.preferences_pb") }
    )

    private val expectedAccepting = true
    private val expectedAcceptingKey = FIELD_ACCEPTING //exactly the same as in the repo

    private val acceptingRepository = AcceptingRepositoryImpl(testDataStore)

    @Test
    fun setAccepted_sets_to_false() = runTest {
        acceptingRepository.setAccepted()

        val actual = testDataStore.data.map {
            it[expectedAcceptingKey]
        }.first()

        assert(false == actual)
    }

    @Test
    fun getAcceptingRequired_returns_expected() = runTest {
        val actual = acceptingRepository.getAcceptingRequired().first()

        assert(expectedAccepting == actual)
    }

    @Before
    fun setup() = runTest {
        testDataStore.edit {
            it[expectedAcceptingKey] = expectedAccepting
        }
    }
}