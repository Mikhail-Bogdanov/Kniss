package com.qwertyuiop.appentrypoint.ui.components.mainActivity.mvi

sealed class MainActivityEvent {

    data object Initialize : MainActivityEvent()

}
