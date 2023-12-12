package com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi

sealed class WhiteEntryPointSideEffect {
    data object NavigateToAccepting : WhiteEntryPointSideEffect()
    data object NavigateToStart : WhiteEntryPointSideEffect()
}