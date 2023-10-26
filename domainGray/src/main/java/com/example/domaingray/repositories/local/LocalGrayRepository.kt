package com.example.domaingray.repositories.local

import kotlinx.coroutines.flow.Flow

interface LocalGrayRepository {

    fun getSavedUrl(): Flow<String?>

    suspend fun saveUrl(url: String)

}