package com.ex.white.ui.composables.main.mvi

sealed class MainEvent {

    data object SettingsClicked : MainEvent()

}