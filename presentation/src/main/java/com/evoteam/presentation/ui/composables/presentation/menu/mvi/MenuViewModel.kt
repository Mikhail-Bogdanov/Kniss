package com.evoteam.presentation.ui.composables.presentation.menu.mvi

import com.evoteam.core.mviViewModel.MviViewModel
import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.useCases.knitting.GetAllKnittingsUseCase
import com.evoteam.domain.useCases.knitting.RemoveKnittingUseCase
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.AddKnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.Initialize
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.KnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.RemoveKnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.SettingsClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToKnitting
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToSettings
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class MenuViewModel(
    private val getAllKnittingsUseCase: GetAllKnittingsUseCase,
    private val removeKnittingUseCase: RemoveKnittingUseCase
) : MviViewModel<MenuState, MenuSideEffect, MenuEvent>(
    initialState = MenuState()
) {
    init {
        dispatch(Initialize)
    }

    override fun dispatch(event: MenuEvent) {
        when (event) {
            AddKnittingClicked -> addKnittingClicked()
            is KnittingClicked -> knittingClicked(event.knitting)
            is RemoveKnittingClicked -> removeKnittingClicked(event.knitting)
            SettingsClicked -> settingsClicked()
            Initialize -> initialize()
        }
    }

    private fun initialize() = intent {
        getAllKnittingsUseCase().collect { knittings ->
            reduce { state.copy(knittings = knittings) }
        }
    }

    private fun addKnittingClicked() = intent {
        postSideEffect(NavigateToStart)
    }

    private fun knittingClicked(knitting: Knitting) = intent {
        postSideEffect(NavigateToKnitting(knitting))
    }

    private fun removeKnittingClicked(knitting: Knitting) = intent {
        removeKnittingUseCase(knitting)
    }

    private fun settingsClicked() = intent {
        postSideEffect(NavigateToSettings)
    }
}