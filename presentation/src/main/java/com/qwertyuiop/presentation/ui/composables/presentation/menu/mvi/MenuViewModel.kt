package com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domain.entities.Knitting
import com.qwertyuiop.domain.useCases.knitting.GetAllKnittingsUseCase
import com.qwertyuiop.domain.useCases.knitting.RemoveKnittingUseCase
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuEvent.AddKnittingClicked
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuEvent.Initialize
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuEvent.KnittingClicked
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuEvent.RemoveKnittingClicked
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuEvent.SettingsClicked
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToKnitting
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToSettings
import com.qwertyuiop.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToStart
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