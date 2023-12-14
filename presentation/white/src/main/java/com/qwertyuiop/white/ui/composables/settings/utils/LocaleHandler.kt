package com.qwertyuiop.white.ui.composables.settings.utils

import androidx.appcompat.app.AppCompatDelegate.getApplicationLocales
import androidx.appcompat.app.AppCompatDelegate.setApplicationLocales
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocaleHandler {

    private val currentLocale
        get() = when (getApplicationLocales().isEmpty) {
            true -> null
            false -> getApplicationLocales()[0]?.language
        }

    suspend fun setLocale(language: Language) = withContext(Dispatchers.Main) {
        setApplicationLocales(language.localeList)
    }
}