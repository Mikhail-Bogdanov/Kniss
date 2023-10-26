package com.example.white.ui.composables.settings.mvi

import androidx.annotation.Keep

@Keep
data class SettingsState(
    val localeDialogShowing: Boolean = false,
    val themeDialogShowing: Boolean = false
)
