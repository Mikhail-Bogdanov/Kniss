package com.qwertyuiop.white.ui.composables.host.mvi


data class HostState(
    val policyAccepted: Boolean = false,
    val termsAccepted: Boolean = false,
    val acceptingDialogShowing: Boolean = false
)