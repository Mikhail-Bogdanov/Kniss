package com.evoteam.presentation.ui.composables.presentation.knitting.mvi

import com.evoteam.core.mviViewModel.MviViewModel
import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.entities.Loop
import com.evoteam.domain.useCases.knitting.RemoveKnittingUseCase
import com.evoteam.domain.useCases.knitting.UpdateKnittingLoopsByIdUseCase
import com.evoteam.domain.useCases.knitting.UpdateKnittingRowByIdUseCase
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.BackButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.EndEditingButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpMenuDismissRequest
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.LoopItemClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.MenuButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowDoneButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowUndoneButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.StartEditingButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.PopBackStack
import com.evoteam.presentation.ui.utils.extensions.updateInnerItem
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class KnittingViewModel(
    private val knitting: Knitting,
    private val updateKnittingRowByIdUseCase: UpdateKnittingRowByIdUseCase,
    private val removeKnittingUseCase: RemoveKnittingUseCase,
    private val updateKnittingLoopsByIdUseCase: UpdateKnittingLoopsByIdUseCase
) : MviViewModel<KnittingState, KnittingSideEffect, KnittingEvent>(
    initialState = KnittingState(
        id = knitting.id,
        loops = knitting.loops,
        currentRow = knitting.currentRow,
        name = knitting.name
    )
) {
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
        val updatedLoops = state.loops.updateInnerItem(loop) {
            Loop(loop.swapType())
        }
        reduce {
            state.copy(
                loops = updatedLoops
            )
        }
        updateKnittingLoopsByIdUseCase(updatedLoops, state.id)
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
        removeKnittingUseCase(knitting)
        reduce { state.copy(isEndDialogShowing = false) }
        postSideEffect(PopBackStack)
    }

    private fun rowDoneButtonClicked() = intent {
        val newCurrentRow = if (state.currentRow == state.loops.size.minus(1))
            state.currentRow
        else
            state.currentRow + 1

        if (state.currentRow != state.loops.size.minus(1))
            updateKnittingRowByIdUseCase(newCurrentRow, state.id)

        reduce {
            state.copy(
                currentRow = newCurrentRow,
                isEndDialogShowing = state.currentRow == state.loops.size.minus(1)
            )
        }
    }

    private fun rowUndoneButtonClicked() = intent {
        updateKnittingRowByIdUseCase(state.currentRow - 1, state.id)

        reduce { state.copy(currentRow = state.currentRow - 1) }
    }

    private fun backButtonClicked() = intent {
        postSideEffect(PopBackStack)
    }
}