package com.ex.gray.ui.gray.mvi

sealed class GraySideEffect {
    data class NavigateToError(
        val message: String
    ) : GraySideEffect()

    data class ShowSnackbar(
        val message: String
    ) : GraySideEffect()

    data object RequestPermissions : GraySideEffect()
}
