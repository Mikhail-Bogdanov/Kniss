package com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi

import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language

data class SettingsState(
    val isDarkTheme: Boolean = false
)

sealed interface SettingsEvent {
    data object BackButtonClicked : SettingsEvent
    data class LanguageClicked(
        val language: Language
    ) : SettingsEvent

    data class ThemeClicked(
        val darkTheme: Boolean
    ) : SettingsEvent

    data object Initialize : SettingsEvent
}

sealed interface SettingsSideEffect {
    data object PopBackStack : SettingsSideEffect
}