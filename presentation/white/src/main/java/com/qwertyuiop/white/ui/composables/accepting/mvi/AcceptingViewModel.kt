package com.qwertyuiop.white.ui.composables.accepting.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptPolicyClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptTermsClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.DoneClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.PolicyLinkClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.TermsLinkClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingSideEffect.NavigateToStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class AcceptingViewModel(
    private val setAcceptedUseCase: SetAcceptedUseCase
) : MviViewModel<AcceptingState, AcceptingSideEffect, AcceptingEvent>(
    initialState = AcceptingState()
) {
    override fun dispatch(event: AcceptingEvent) {
        when (event) {
            DoneClicked -> doneClicked()
            AcceptPolicyClicked -> acceptPolicyClicked()
            PolicyLinkClicked -> policyLinkClicked()
            AcceptTermsClicked -> acceptTermsClicked()
            TermsLinkClicked -> termsLinkClicked()
        }
    }

    private fun doneClicked() = intent {
        setAcceptedUseCase()
        postSideEffect(NavigateToStart)
    }

    private fun acceptPolicyClicked() = intent {
        reduce {
            state.copy(policyAccepted = !state.policyAccepted)
        }
    }

    private fun policyLinkClicked() = intent {
        postSideEffect(AcceptingSideEffect.OpenPolicy)
    }

    private fun acceptTermsClicked() = intent {
        reduce {
            state.copy(termsAccepted = !state.termsAccepted)
        }
    }

    private fun termsLinkClicked() = intent {
        postSideEffect(AcceptingSideEffect.OpenTerms)
    }
}