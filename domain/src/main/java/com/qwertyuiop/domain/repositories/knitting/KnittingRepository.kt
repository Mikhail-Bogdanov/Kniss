package com.qwertyuiop.domain.repositories.knitting

import com.qwertyuiop.domain.entities.Knitting
import com.qwertyuiop.domain.entities.Loop
import kotlinx.coroutines.flow.Flow

interface KnittingRepository {
    fun getAllKnittings(): Flow<List<Knitting>>

    suspend fun addKnitting(knitting: Knitting)

    suspend fun removeKnitting(knitting: Knitting)

    suspend fun updateCurrentRowById(currentRow: Int, id: String)

    suspend fun updateLoopsById(loops: List<List<Loop>>, id: String)
}