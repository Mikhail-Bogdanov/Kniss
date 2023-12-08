package com.qwertyuiop.white.ui.composables.main.mvi

sealed class MainEvent {

    data object SettingsClicked : MainEvent()

}