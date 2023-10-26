package com.example.gray.ui.gray.mvi

sealed class GraySideEffect {
    data class NavigateToError(
        val message: String
    ) : GraySideEffect()
}
