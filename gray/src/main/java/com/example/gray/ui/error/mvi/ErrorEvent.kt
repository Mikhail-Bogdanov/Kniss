package com.example.gray.ui.error.mvi

import androidx.annotation.Keep

sealed class ErrorEvent {
    data object UpdateRequest : ErrorEvent()

    @Keep
    data class UpdateSslRequest(
        val onCLick: () -> Unit
    ) : ErrorEvent()

    data object ChangeToGray : ErrorEvent()
}