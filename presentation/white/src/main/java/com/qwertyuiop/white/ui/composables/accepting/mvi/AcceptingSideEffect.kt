package com.qwertyuiop.white.ui.composables.accepting.mvi

sealed class AcceptingSideEffect {
    data object OpenTerms : AcceptingSideEffect()
    data object OpenPolicy : AcceptingSideEffect()
    data object NavigateToStart : AcceptingSideEffect()
}