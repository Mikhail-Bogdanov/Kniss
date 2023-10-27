package com.example.gray.ui.error.mvi

import androidx.annotation.Keep

sealed class ErrorSideEffect {
    data object NavigateToGray : ErrorSideEffect()

    @Keep
    data class ShowSnackBar(
        val message: String
    ) : ErrorSideEffect()
}
