package com.ex.gray.ui.error.mvi

sealed class ErrorEvent {
    data object UpdateRequest : ErrorEvent()
    data object UpdateSslRequest : ErrorEvent()
}