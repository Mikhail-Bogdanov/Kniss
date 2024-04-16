package com.evoteam.presentation.ui.composables.presentation.menu.mvi

import com.evoteam.domain.entities.Knitting

data class MenuState(
    val knittings: List<Knitting> = emptyList()
)

sealed interface MenuEvent {
    data object SettingsClicked : MenuEvent
    data object AddKnittingClicked : MenuEvent
    data class KnittingClicked(
        val knitting: Knitting
    ) : MenuEvent

    data class RemoveKnittingClicked(
        val knitting: Knitting
    ) : MenuEvent

    data object Initialize : MenuEvent
}

sealed interface MenuSideEffect {
    data object NavigateToSettings : MenuSideEffect
    data object NavigateToStart : MenuSideEffect
    data class NavigateToKnitting(
        val knitting: Knitting
    ) : MenuSideEffect
}