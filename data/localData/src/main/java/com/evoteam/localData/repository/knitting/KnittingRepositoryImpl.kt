package com.evoteam.localData.repository.knitting

import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.entities.Loop
import com.evoteam.domain.repositories.knitting.KnittingRepository
import com.evoteam.localData.database.KnittingDao
import com.evoteam.localData.utils.mappers.EntityToDtoMapper.toKnittingDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class KnittingRepositoryImpl(
    private val knittingDao: KnittingDao
) : KnittingRepository {
    override fun getAllKnittings() = knittingDao.selectAll().map {
        it.map { it.toKnittingEntity() }
    }

    override suspend fun addKnitting(knitting: Knitting) = withContext(Dispatchers.IO) {
        knittingDao.insertKnittingDto(knitting.toKnittingDto())
    }

    override suspend fun removeKnitting(knitting: Knitting) = withContext(Dispatchers.IO) {
        knittingDao.deleteById(knitting.id)
    }

    override suspend fun updateCurrentRowById(
        currentRow: Int,
        id: String
    ) = withContext(Dispatchers.IO) {
        knittingDao.updateCurrentRowById(currentRow, id)
    }

    override suspend fun updateLoopsById(
        loops: List<List<Loop>>,
        id: String
    ) = withContext(Dispatchers.IO) {
        knittingDao.updateLoopsById(loops, id)
    }
}