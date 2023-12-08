package com.qwertyuiop.white.ui.composables.accepting.mvi


data class AcceptingState(
    val policyAccepted: Boolean = false,
    val termsAccepted: Boolean = false,
    val showAccepting: Boolean = false
)