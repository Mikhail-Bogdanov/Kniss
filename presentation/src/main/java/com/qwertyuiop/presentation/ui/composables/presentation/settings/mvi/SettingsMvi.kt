package com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi

import androidx.appcompat.app.AppCompatDelegate.getApplicationLocales
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language

data class SettingsState(
    val isDarkTheme: Boolean = false,
    val isLanguagesExpanded: Boolean = false
) {
    val currentLanguage
        get() = Language.getByTag(getApplicationLocales().toLanguageTags())
}

sealed interface SettingsEvent {
    data object BackButtonClicked : SettingsEvent
    data class LanguageClicked(
        val language: Language
    ) : SettingsEvent

    data class ThemeClicked(
        val darkTheme: Boolean
    ) : SettingsEvent

    data object Initialize : SettingsEvent
    data object ExpandLanguagesClicked : SettingsEvent
}

sealed interface SettingsSideEffect {
    data object PopBackStack : SettingsSideEffect
}