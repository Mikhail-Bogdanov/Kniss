package com.qwertyuiop.entrypoint.ui.mainActivity.mvi

sealed class MainActivityEvent {

    data object Initialize : MainActivityEvent()

}
