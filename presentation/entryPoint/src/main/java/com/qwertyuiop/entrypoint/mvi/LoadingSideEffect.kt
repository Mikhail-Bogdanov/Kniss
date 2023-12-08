package com.qwertyuiop.entrypoint.mvi

sealed class LoadingSideEffect {
    data object NavigateToGray : LoadingSideEffect()
    data object NavigateToWhite : LoadingSideEffect()
}