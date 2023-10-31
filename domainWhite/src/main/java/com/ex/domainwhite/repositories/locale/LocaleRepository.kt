package com.ex.domainwhite.repositories.locale

import kotlinx.coroutines.flow.Flow

interface LocaleRepository {

    fun getSavedLocale(): Flow<String>

    suspend fun saveLocale(locale: String)

}