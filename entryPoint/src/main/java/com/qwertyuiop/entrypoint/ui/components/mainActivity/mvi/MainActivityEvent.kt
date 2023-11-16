package com.qwertyuiop.entrypoint.ui.components.mainActivity.mvi

sealed class MainActivityEvent {

    data object Initialize : MainActivityEvent()

}
