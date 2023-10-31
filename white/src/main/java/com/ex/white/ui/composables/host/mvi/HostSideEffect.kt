package com.ex.white.ui.composables.host.mvi

sealed class HostSideEffect {
    data object OpenTerms : HostSideEffect()
    data object OpenPolicy : HostSideEffect()
}
