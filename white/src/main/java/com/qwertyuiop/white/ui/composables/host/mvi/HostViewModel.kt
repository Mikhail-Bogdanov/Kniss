package com.qwertyuiop.white.ui.composables.host.mvi

import androidx.appcompat.app.AppCompatDelegate.setApplicationLocales
import androidx.core.os.LocaleListCompat.forLanguageTags
import androidx.lifecycle.viewModelScope
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.accepting.GetAcceptingRequiredUseCase
import com.qwertyuiop.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.qwertyuiop.domainwhite.useCases.locale.GetSavedLocaleUseCase
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.AcceptPolicyClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.AcceptTermsClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.AcceptingDialogDoneClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.Initialize
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.PolicyLinkClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.TermsLinkClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostSideEffect.OpenPolicy
import com.qwertyuiop.white.ui.composables.host.mvi.HostSideEffect.OpenTerms
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class HostViewModel(
    private val getSavedLocaleUseCase: GetSavedLocaleUseCase,
    private val getAcceptingRequiredUseCase: GetAcceptingRequiredUseCase,
    private val setAcceptedUseCase: SetAcceptedUseCase
) : MviViewModel<HostState, HostSideEffect, HostEvent>(
    initialState = HostState()
) {

    init {
        dispatch(Initialize)
    }

    override fun dispatch(event: HostEvent) {
        when (event) {
            Initialize -> initialize()
            AcceptingDialogDoneClicked -> acceptingDialogDoneClicked()
            AcceptPolicyClicked -> acceptPolicyClicked()
            PolicyLinkClicked -> policyLinkClicked()
            AcceptTermsClicked -> acceptTermsClicked()
            TermsLinkClicked -> termsLinkClicked()
        }
    }

    private fun acceptingDialogDoneClicked() = intent {
        setAcceptedUseCase()
    }

    private fun acceptPolicyClicked() = intent {
        reduce {
            state.copy(policyAccepted = !state.policyAccepted)
        }
    }

    private fun policyLinkClicked() = intent {
        postSideEffect(OpenPolicy)
    }

    private fun acceptTermsClicked() = intent {
        reduce {
            state.copy(termsAccepted = !state.termsAccepted)
        }
    }

    private fun termsLinkClicked() = intent {
        postSideEffect(OpenTerms)
    }

    private fun initialize() = intent {
        collectLocale()
        collectAccepting()
    }

    private fun SimpleSyntax<HostState, HostSideEffect>.collectAccepting() {
        getAcceptingRequiredUseCase().onEach { acceptingRequired ->
            reduce { state.copy(acceptingDialogShowing = acceptingRequired) }
        }.launchIn(viewModelScope)
    }

    private fun collectLocale() {
        getSavedLocaleUseCase().onEach { locale ->
            setApplicationLocales(forLanguageTags(locale))
        }.launchIn(viewModelScope)
    }
}