package com.ex.white.ui.composables.start.mvi

sealed class StartSideEffect {
    data object NavigateToMain : StartSideEffect()
    data object NavigateToSettings : StartSideEffect()
}