package com.qwertyuiop.localData.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.localData.dtos.KnittingDto
import kotlinx.coroutines.flow.Flow

@Dao
interface KnittingDao {

    @Query("SELECT * FROM knitting_table")
    fun selectAll(): Flow<List<KnittingDto>>

    @Query("DELETE FROM knitting_table WHERE id = :id")
    suspend fun deleteById(id: String)

    @Insert
    suspend fun insertKnittingDto(knittingDto: KnittingDto)

    @Query("UPDATE knitting_table SET currentRow = :currentRow WHERE id = :id")
    suspend fun updateCurrentRowById(currentRow: Int, id: String)

    @Query("UPDATE knitting_table SET loops = :loops WHERE id = :id")
    suspend fun updateLoopsById(loops: List<List<Loop>>, id: String)
}