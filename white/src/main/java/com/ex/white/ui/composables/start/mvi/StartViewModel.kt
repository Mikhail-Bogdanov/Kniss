package com.ex.white.ui.composables.start.mvi

import com.ex.core.mviViewModel.MviViewModel
import com.ex.white.ui.composables.start.mvi.StartEvent.SettingsButtonClicked
import com.ex.white.ui.composables.start.mvi.StartEvent.StartButtonClicked
import com.ex.white.ui.composables.start.mvi.StartSideEffect.NavigateToMain
import com.ex.white.ui.composables.start.mvi.StartSideEffect.NavigateToSettings
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class StartViewModel : MviViewModel<StartState, StartSideEffect, StartEvent>(
    initialState = StartState()
) {
    override fun dispatch(event: StartEvent) {
        when (event) {
            StartButtonClicked -> startButtonClicked()
            SettingsButtonClicked -> settingsButtonClicked()
        }
    }

    private fun settingsButtonClicked() = intent {
        postSideEffect(NavigateToSettings)
    }

    private fun startButtonClicked() = intent {
        postSideEffect(NavigateToMain)
    }
}