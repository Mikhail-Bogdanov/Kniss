package com.example.white.ui.composables.start.mvi

import com.example.white.ui.composables.start.mvi.StartEvent.SettingsButtonClicked
import com.example.white.ui.composables.start.mvi.StartEvent.StartButtonClicked
import com.example.white.ui.composables.start.mvi.StartSideEffect.NavigateToMain
import com.example.white.ui.composables.start.mvi.StartSideEffect.NavigateToSettings
import com.example.white.ui.mviViewModel.MviViewModel
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