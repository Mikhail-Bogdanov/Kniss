package com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.BackButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.EndEditingButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpMenuDismissRequest
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.LoopItemClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.MenuButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowDoneButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowUndoneButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.StartEditingButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.PopBackStack
import com.qwertyuiop.presentation.ui.composables.presentation.shared.KnittingPatternState
import com.qwertyuiop.presentation.ui.utils.extensions.updateInnerItem
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class KnittingViewModel(
    knittingPatternState: KnittingPatternState
) : MviViewModel<KnittingState, KnittingSideEffect, KnittingEvent>(
    initialState = KnittingState()
) {
    init {
        val stampHeight = knittingPatternState.pattern.size

        val stamp = MutableList(stampHeight) { rowIndex ->
            val loopsInRow = knittingPatternState.pattern[rowIndex].size
            var currentLoop = -1
            MutableList(knittingPatternState.width) {
                if (currentLoop++ == loopsInRow.minus(1)) currentLoop = 0
                knittingPatternState.pattern[rowIndex][currentLoop].toLoop()
            }
        }

        var currentRow = -1
        val updatedLoops = List(knittingPatternState.height) {
            if (currentRow++ == stampHeight.minus(1)) currentRow = 0
            stamp[currentRow].map { Loop(it.type) } //to recreate object reference
        }

        intent {
            reduce {
                state.copy(
                    loops = updatedLoops,
                    currentRow = 0
                )
            }
        }
    }

    override fun dispatch(event: KnittingEvent) {
        when (event) {
            BackButtonClicked -> backButtonClicked()
            RowDoneButtonClicked -> rowDoneButtonClicked()
            RowUndoneButtonClicked -> rowUndoneButtonClicked()
            MenuButtonClicked -> menuButtonClicked()
            EndEditingButtonClicked -> endEditingButtonClicked()
            HelpButtonClicked -> helpButtonClicked()
            HelpMenuDismissRequest -> helpMenuDismissRequest()
            StartEditingButtonClicked -> startEditingButtonClicked()
            is LoopItemClicked -> loopItemClicked(event.loop)
        }
    }

    private fun loopItemClicked(loop: Loop) = intent {
        reduce {
            state.copy(
                loops = state.loops.updateInnerItem(loop) { Loop(loop.swapType()) }
            )
        }
    }

    private fun endEditingButtonClicked() = intent {
        reduce { state.copy(isInEdit = false) }
    }

    private fun helpButtonClicked() = intent {
        reduce { state.copy(isHelpMenuOpened = true) }
    }

    private fun helpMenuDismissRequest() = intent {
        reduce { state.copy(isHelpMenuOpened = false) }
    }

    private fun startEditingButtonClicked() = intent {
        reduce { state.copy(isInEdit = true) }
    }

    private fun menuButtonClicked() = intent {
        reduce { state.copy(isEndDialogShowing = false) }
        postSideEffect(NavigateToStart)
    }

    private fun rowDoneButtonClicked() = intent {
        reduce {
            state.copy(
                currentRow = state.currentRow + 1,
                isEndDialogShowing = state.currentRow == state.loops.size.minus(1)
            )
        }
    }

    private fun rowUndoneButtonClicked() = intent {
        reduce { state.copy(currentRow = state.currentRow - 1) }
    }

    private fun backButtonClicked() = intent {
        postSideEffect(PopBackStack)
    }
}