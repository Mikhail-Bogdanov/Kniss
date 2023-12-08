package com.qwertyuiop.white.ui.composables.settings.mvi

sealed class SettingsEvent {
    data object BackButtonClicked : SettingsEvent()
    data object RateUsClicked : SettingsEvent()
    data object WriteUsClicked : SettingsEvent()
    data object TermsClicked : SettingsEvent()
    data object PolicyClicked : SettingsEvent()
    data object HideLocaleDialogClicked : SettingsEvent()
    data object HideThemeDialogClicked : SettingsEvent()
    data object ShowLocaleDialogClicked : SettingsEvent()
    data object ShowThemeDialogClicked : SettingsEvent()


    data class LocaleClicked(
        val locale: String
    ) : SettingsEvent()


    data class ThemeClicked(
        val darkTheme: Boolean
    ) : SettingsEvent()
}