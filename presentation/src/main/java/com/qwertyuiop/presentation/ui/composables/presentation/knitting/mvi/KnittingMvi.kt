package com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi

import com.qwertyuiop.domain.entities.Loop

data class KnittingState(
    val currentRow: Int = -1,
    val loops: List<List<Loop>> = emptyList(), //list of rows
    val isEndDialogShowing: Boolean = false,
    val isInEdit: Boolean = false,
    val isHelpMenuOpened: Boolean = false,
    val id: String,
    val name: String
)

sealed interface KnittingEvent {
    data object RowDoneButtonClicked : KnittingEvent
    data object RowUndoneButtonClicked : KnittingEvent
    data object BackButtonClicked : KnittingEvent
    data object MenuButtonClicked : KnittingEvent
    data object HelpButtonClicked : KnittingEvent
    data object HelpMenuDismissRequest : KnittingEvent
    data object StartEditingButtonClicked : KnittingEvent
    data object EndEditingButtonClicked : KnittingEvent
    data class LoopItemClicked(
        val loop: Loop
    ) : KnittingEvent
}

sealed interface KnittingSideEffect {
    data object PopBackStack : KnittingSideEffect
}