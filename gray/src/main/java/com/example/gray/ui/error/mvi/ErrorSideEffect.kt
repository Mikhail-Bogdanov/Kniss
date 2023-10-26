package com.example.gray.ui.error.mvi

sealed class ErrorSideEffect {
    data object NavigateToGray : ErrorSideEffect()
}
