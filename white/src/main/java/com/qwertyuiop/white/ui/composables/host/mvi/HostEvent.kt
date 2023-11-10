package com.qwertyuiop.white.ui.composables.host.mvi

sealed class HostEvent {
    data object Initialize : HostEvent()
    data object AcceptPolicyClicked : HostEvent()
    data object AcceptTermsClicked : HostEvent()
    data object PolicyLinkClicked : HostEvent()
    data object TermsLinkClicked : HostEvent()
    data object AcceptingDialogDoneClicked : HostEvent()
}