package com.repositories.databaseWhite

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.qwertyuiop.domainwhite.entities.AppEntity
import com.qwertyuiop.localdatawhite.database.AppDatabase
import com.qwertyuiop.localdatawhite.di.TestLocalWhiteModule
import com.qwertyuiop.localdatawhite.dtos.AppDto
import com.qwertyuiop.localdatawhite.repository.database.DatabaseWhiteRepositoryImpl
import com.qwertyuiop.localdatawhite.utils.WhiteLocalMapper.toAppDto
import com.qwertyuiop.localdatawhite.utils.WhiteLocalMapper.toAppEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class DatabaseWhiteRepositoryTest {
    companion object {
        init {
            startKoin {
                androidContext(getInstrumentation().context.applicationContext)
                modules(
                    TestLocalWhiteModule.module
                )
            }
        }

        private val testEntities = listOf(
            AppDto("1", "imba"), AppDto("2", "huy"),
            AppDto("3", "pEnis"), AppDto("4", "[pisya]"),
        )

        private val database: AppDatabase by inject(
            AppDatabase::class.java,
            named("test")
        )
        private val dao = database.appDao
        private val databaseRepository = DatabaseWhiteRepositoryImpl(dao)

        @AfterClass
        @JvmStatic
        fun clearBD() = runTest {
            database.clearAllTables()
            database.close()
            stopKoin()
        }


    }

    @Before
    fun resetDB() = runTest {
        database.clearAllTables()
        testEntities.forEach {
            dao.insertEntity(it)
        }
    }

    @Test
    fun getAllEntities_returns_flow_entities() = runTest {
        val expected = testEntities.map { it.toAppEntity() }

        val actual = databaseRepository.getAllEntities().first()

        assert(expected == actual)
    }

    @Test
    fun addEntity_inserts_entity() = runTest {
        val newEntity = AppEntity(name = "HUY")
        dao.insertEntity(newEntity.toAppDto())

        val expected = testEntities.map { it.toAppEntity() }.toMutableList().apply {
            add(newEntity)
        }.map { it.toAppDto() }

        val actual = dao.getAllEntities().first()

        assert(expected == actual)
    }

    @Test
    fun removeEntity_removes_entity() = runTest {
        val entityToDelete = testEntities.random()
        dao.deleteEntity(entityToDelete)

        val expected = testEntities.toMutableList().apply {
            remove(entityToDelete)
        }

        val actual = dao.getAllEntities().first()

        assert(expected == actual)
    }

    @Test
    fun updateEntity_updates_entity() = runTest {
        var entityToUpdate = testEntities.random()
        val index = testEntities.indexOf(entityToUpdate)
        entityToUpdate = entityToUpdate.copy(
            name = "AUE"
        )
        dao.updateEntity(entityToUpdate)

        val expected = testEntities.toMutableList().apply {
            set(index, entityToUpdate)
        }

        val actual = dao.getAllEntities().first()

        assertEquals(expected, actual)
    }
}