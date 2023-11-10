package com.qwertyuiop.white.ui.composables.start.mvi

sealed class StartEvent {
    data object StartButtonClicked : StartEvent()
    data object SettingsButtonClicked : StartEvent()
}