package com.qwertyuiop.white.ui.composables.main.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.white.ui.composables.main.mvi.MainEvent.SettingsClicked
import com.qwertyuiop.white.ui.composables.main.mvi.MainSideEffect.NavigateToSettings
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