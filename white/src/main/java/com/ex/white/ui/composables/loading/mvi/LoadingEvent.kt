package com.ex.white.ui.composables.loading.mvi

sealed class LoadingEvent {
    data object ChangeToGray : LoadingEvent()
    data object ChangeToWhite : LoadingEvent()
    data object GetRequest : LoadingEvent()
}