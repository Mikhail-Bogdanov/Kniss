package com.qwertyuiop.white.ui.composables.accepting.mvi

sealed class AcceptingEvent {
    data object Initialize : AcceptingEvent()
    data object AcceptPolicyClicked : AcceptingEvent()
    data object AcceptTermsClicked : AcceptingEvent()
    data object PolicyLinkClicked : AcceptingEvent()
    data object TermsLinkClicked : AcceptingEvent()
    data object DoneClicked : AcceptingEvent()
}