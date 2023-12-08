package com.qwertyuiop.appentrypoint.ui.components.mainActivity.mvi

import androidx.lifecycle.viewModelScope
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.theme.GetSavedThemeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class MainActivityViewModel(
    private val getSavedThemeUseCase: GetSavedThemeUseCase
) : MviViewModel<MainActivityState, Any, MainActivityEvent>(
    initialState = MainActivityState()
) {

    init {
        dispatch(MainActivityEvent.Initialize)
    }

    override fun dispatch(event: MainActivityEvent) {
        when (event) {
            MainActivityEvent.Initialize -> initialize()
        }
    }

    private fun initialize() = intent {
        collectTheme()
    }

    private fun SimpleSyntax<MainActivityState, Any>.collectTheme() {
        getSavedThemeUseCase().onEach { darkTheme ->
            reduce { state.copy(darkTheme = darkTheme) }
        }.launchIn(viewModelScope)
    }
}