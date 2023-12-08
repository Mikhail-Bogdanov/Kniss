package com.qwertyuiop.entrypoint.mvi

sealed class LoadingEvent {
    data object GetRequest : LoadingEvent()
}