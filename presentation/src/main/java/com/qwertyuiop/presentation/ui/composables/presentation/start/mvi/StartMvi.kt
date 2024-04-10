package com.qwertyuiop.presentation.ui.composables.presentation.start.mvi

import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.presentation.ui.composables.presentation.shared.KnittingPatternState

data class StartState(
    val width: Int? = null,
    val height: Int? = null,
    val loops: List<List<Loop>> = listOf(listOf(Loop())),
    val stampQuestionOpened: Boolean = false
) {
    val isCorrect
        get() = width != null &&
                height != null &&
                loops.size <= height.toInt() &&
                loops.all { it.size <= width.toInt() }
}

sealed interface StartEvent {
    data class WidthInput(
        val valueString: String
    ) : StartEvent

    data class HeightInput(
        val valueString: String
    ) : StartEvent

    data class AddLoopClicked(
        val rowIndex: Int
    ) : StartEvent

    data class RemoveLoopClicked(
        val loop: Loop
    ) : StartEvent

    data class LoopClicked(
        val loop: Loop
    ) : StartEvent

    data object AddRowClicked : StartEvent
    data object DoneClicked : StartEvent
    data object StampQuestionMarkClicked : StartEvent
    data object StampQuestionCloseRequested : StartEvent
    data object SettingsClicked : StartEvent
}

sealed interface StartSideEffect {
    data class NavigateToKnitting(
        val knittingPatternState: KnittingPatternState
    ) : StartSideEffect

    data object NavigateToSettings : StartSideEffect
}