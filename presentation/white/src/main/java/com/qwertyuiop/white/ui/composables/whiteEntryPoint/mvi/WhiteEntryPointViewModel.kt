package com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.accepting.GetAcceptingRequiredUseCase
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi.WhiteEntryPointEvent.Initialize
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi.WhiteEntryPointSideEffect.NavigateToAccepting
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi.WhiteEntryPointSideEffect.NavigateToStart
import kotlinx.coroutines.flow.first
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class WhiteEntryPointViewModel(
    private val getAcceptingRequiredUseCase: GetAcceptingRequiredUseCase
) : MviViewModel<Any, WhiteEntryPointSideEffect, WhiteEntryPointEvent>(
    initialState = Any()
) {
    init {
        dispatch(Initialize)
    }

    override fun dispatch(event: WhiteEntryPointEvent) {
        when (event) {
            Initialize -> initialize()
        }
    }

    private fun initialize() = intent {
        when (getAcceptingRequiredUseCase().first()) {
            true -> postSideEffect(NavigateToAccepting)
            false -> postSideEffect(NavigateToStart)
        }
    }
}