package com.ex.white.ui.composables.host.mvi


data class HostState(
    val darkTheme: Boolean = false,
    val policyAccepted: Boolean = false,
    val termsAccepted: Boolean = false,
    val acceptingDialogShowing: Boolean = true //TODO SHOWING DIALOG
)