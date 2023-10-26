package com.example.localdatawhite.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.localdatawhite.dto.AppDto
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM appTable")
    fun getAllEntities(): Flow<List<AppDto>>

    @Insert
    suspend fun insertEntity(appDto: AppDto)

    @Delete
    suspend fun deleteEntity(appDto: AppDto)

    @Update
    suspend fun updateEntity(appDto: AppDto)

}