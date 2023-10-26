package com.example.white.ui.composables.host.mvi

import androidx.annotation.Keep

@Keep
data class HostState(
    val darkTheme: Boolean = false,
    val policyAccepted: Boolean = false,
    val termsAccepted: Boolean = false,
    val acceptingDialogShowing: Boolean = true //TODO SHOWING DIALOG
)