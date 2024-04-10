package com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi

import com.qwertyuiop.domain.entities.Loop

data class KnittingState(
    val currentRow: Int = -1,
    val loops: List<List<Loop>> = emptyList(), //list of rows
    val isEndDialogShowing: Boolean = false
)

sealed interface KnittingEvent {
    data object RowDoneButtonClicked : KnittingEvent
    data object RowUndoneButtonClicked : KnittingEvent
    data object BackButtonClicked : KnittingEvent
    data object MenuButtonClicked : KnittingEvent
}

sealed interface KnittingSideEffect {
    data object PopBackStack : KnittingSideEffect
    data object NavigateToStart : KnittingSideEffect
}