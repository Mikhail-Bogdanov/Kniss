package com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.BackButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowDoneButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowUndoneButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.PopBackStack
import com.qwertyuiop.presentation.ui.composables.presentation.shared.KnittingPatternState
import org.orbitmvi.orbit.syntax.simple.blockingIntent
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
            stamp[currentRow]
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
            KnittingEvent.MenuButtonClicked -> menuButtonClicked()
        }
    }

    private fun menuButtonClicked() = blockingIntent {
        reduce { state.copy(currentRow = -1) } //just for beauty
        postSideEffect(NavigateToStart)
    }

    private fun rowDoneButtonClicked() = intent {
        reduce { state.copy(currentRow = state.currentRow + 1) }
    }

    private fun rowUndoneButtonClicked() = intent {
        reduce { state.copy(currentRow = state.currentRow - 1) }
    }

    private fun backButtonClicked() = intent {
        postSideEffect(PopBackStack)
    }
}