package com.qwertyuiop.white.ui.composables.settings.mvi

sealed class SettingsSideEffect {
    data object PopBackStack : SettingsSideEffect()
    data object OpenPlayMarket : SettingsSideEffect()
    data object OpenMail : SettingsSideEffect()
    data object OpenTerms : SettingsSideEffect()
    data object OpenPolicy : SettingsSideEffect()
}