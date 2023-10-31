package com.ex.white.ui.composables.loading.mvi

sealed class LoadingSideEffect {
    data object NavigateToGray : LoadingSideEffect()
    data object NavigateToWhite : LoadingSideEffect()
}