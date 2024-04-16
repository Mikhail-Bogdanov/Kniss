package com.evoteam.domain.repositories.knitting

import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.entities.Loop
import kotlinx.coroutines.flow.Flow

interface KnittingRepository {
    fun getAllKnittings(): Flow<List<Knitting>>

    suspend fun addKnitting(knitting: Knitting)

    suspend fun removeKnitting(knitting: Knitting)

    suspend fun updateCurrentRowById(currentRow: Int, id: String)

    suspend fun updateLoopsById(loops: List<List<Loop>>, id: String)
}