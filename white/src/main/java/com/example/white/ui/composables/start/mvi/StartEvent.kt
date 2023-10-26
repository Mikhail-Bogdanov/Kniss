package com.example.white.ui.composables.start.mvi

sealed class StartEvent {
    data object StartButtonClicked : StartEvent()
    data object SettingsButtonClicked : StartEvent()
}