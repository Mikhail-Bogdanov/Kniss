package com.example.white.ui.composables.main.mvi

import com.example.white.ui.composables.main.mvi.MainEvent.SettingsClicked
import com.example.white.ui.composables.main.mvi.MainSideEffect.NavigateToSettings
import com.example.white.ui.mviViewModel.MviViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class MainViewModel : MviViewModel<MainState, MainSideEffect, MainEvent>(
    initialState = MainState()
) {
    override fun dispatch(event: MainEvent) {
        when (event) {
            SettingsClicked -> settingsClicked()
        }
    }

    private fun settingsClicked() = intent {
        postSideEffect(NavigateToSettings)
    }
}