package com.qwertyuiop.presentation.ui.composables.presentation.start.mvi

import com.qwertyuiop.domain.entities.Loop

data class StartState(
    val width: Int? = null,
    val height: Int? = null,
    val loops: List<List<Loop>> = listOf(listOf(Loop())),
    val stampQuestionOpened: Boolean = false,
    val name: String = ""
) {
    val isCorrect
        get() = width != null &&
                height != null &&
                name.isNotBlank() &&
                name.isNotEmpty() &&
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

    data class NameInput(
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
    data object BackButtonClicked : StartEvent
}

sealed interface StartSideEffect {
    data object PopBackStack : StartSideEffect

    data object NavigateToSettings : StartSideEffect
}