package com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi

sealed class WhiteEntryPointEvent {
    data object Initialize : WhiteEntryPointEvent()
}