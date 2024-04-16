package com.evoteam.domain.repositories.theme

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    fun getSavedTheme(): Flow<Boolean>

    suspend fun saveTheme(darkTheme: Boolean)

}