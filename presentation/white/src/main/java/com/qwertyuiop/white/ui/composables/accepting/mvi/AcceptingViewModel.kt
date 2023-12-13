package com.qwertyuiop.white.ui.composables.accepting.mvi

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewModelScope
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.qwertyuiop.domainwhite.useCases.locale.GetSavedLocaleUseCase
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptPolicyClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptTermsClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.DoneClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.Initialize
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.PolicyLinkClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.TermsLinkClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingSideEffect.NavigateToStart
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class AcceptingViewModel(
    private val setAcceptedUseCase: SetAcceptedUseCase,
    private val getSavedLocaleUseCase: GetSavedLocaleUseCase
) : MviViewModel<AcceptingState, AcceptingSideEffect, AcceptingEvent>(
    initialState = AcceptingState()
) {
    init {
        dispatch(Initialize)
    }

    override fun dispatch(event: AcceptingEvent) {
        when (event) {
            Initialize -> initialize()
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

    private fun initialize() = intent {
        collectLocale()
    }

    private fun collectLocale() {
        getSavedLocaleUseCase().onEach { locale ->
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
        }.launchIn(viewModelScope)
    }
}