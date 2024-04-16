@file:OptIn(OrbitExperimental::class)

package com.evoteam.presentation.ui.composables.presentation.start.mvi

import android.annotation.SuppressLint
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import com.evoteam.core.mviViewModel.MviViewModel
import com.evoteam.domain.entities.Loop
import com.evoteam.domain.useCases.knitting.AddKnittingUseCase
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.AddLoopClicked
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.AddRowClicked
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.DoneClicked
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.HeightInput
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.RemoveLoopClicked
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.WidthInput
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartSideEffect.NavigateToSettings
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartSideEffect.PopBackStack
import com.evoteam.presentation.ui.utils.UtilsFunctions.generateKnitting
import com.evoteam.presentation.ui.utils.extensions.extend
import com.evoteam.presentation.ui.utils.extensions.extendInner
import com.evoteam.presentation.ui.utils.extensions.isBlankOrEmpty
import com.evoteam.presentation.ui.utils.extensions.removeWherever
import com.evoteam.presentation.ui.utils.extensions.updateInnerItem
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class StartViewModel(
    private val vibrator: Vibrator,
    private val addKnittingUseCase: AddKnittingUseCase
) : MviViewModel<StartState, StartSideEffect, StartEvent>(
    initialState = StartState()
) {
    override fun dispatch(event: StartEvent) {
        when (event) {
            DoneClicked -> doneClicked()
            is HeightInput -> heightInput(event.valueString)
            is WidthInput -> widthInput(event.valueString)
            is AddLoopClicked -> addLoopClicked(event.rowIndex)
            AddRowClicked -> addRowClicked()
            is RemoveLoopClicked -> removeLoopClicked(event.loop)
            is StartEvent.LoopClicked -> loopClicked(event.loop)
            StartEvent.StampQuestionCloseRequested -> stampQuestionCloseRequested()
            StartEvent.StampQuestionMarkClicked -> stampQuestionMarkClicked()
            StartEvent.SettingsClicked -> settingsClicked()
            is StartEvent.NameInput -> nameInput(event.valueString)
            StartEvent.BackButtonClicked -> backButtonClicked()
        }
    }

    private fun backButtonClicked() = intent {
        postSideEffect(PopBackStack)
    }

    private fun nameInput(nameString: String) = blockingIntent {
        if (nameString.length in 0..15)
            reduce { state.copy(name = nameString) }
    }

    private fun settingsClicked() = intent {
        postSideEffect(NavigateToSettings)
    }

    private fun stampQuestionCloseRequested() = intent {
        reduce { state.copy(stampQuestionOpened = false) }
    }

    private fun stampQuestionMarkClicked() = intent {
        reduce { state.copy(stampQuestionOpened = !state.stampQuestionOpened) }
    }

    private fun loopClicked(loop: Loop) = intent {
        reduce {
            state.copy(
                loops = state.loops.updateInnerItem(loop) { Loop(loop.swapType()) }
            )
        }
    }

    private fun addRowClicked() = intent {
        reduce {
            state.copy(
                loops = state.loops.extend(listOf(Loop()))
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun removeLoopClicked(loop: Loop) = intent {
        if (state.loops.any { it.size > 1 } || state.loops.size > 1) {
            reduce {
                state.copy(
                    loops = state.loops.removeWherever(loop)
                )
            }
            vibrator.vibrate(VibrationEffect.createOneShot(100L, DEFAULT_AMPLITUDE))
        }
    }

    private fun addLoopClicked(rowIndex: Int) = intent {
        reduce {
            state.copy(
                loops = state.loops.extendInner(rowIndex, Loop())
            )
        }
    }

    private fun heightInput(heightString: String) = blockingIntent {
        when {
            heightString.isBlankOrEmpty() -> {
                reduce { state.copy(height = null) }
                return@blockingIntent
            }

            heightString.toIntOrNull() == null -> return@blockingIntent
        }
        val height = heightString.toInt()
        if (height !in 0..100) return@blockingIntent
        reduce { state.copy(height = height) }
    }

    private fun widthInput(widthString: String) = blockingIntent {
        when {
            widthString.isBlankOrEmpty() -> {
                reduce { state.copy(width = null) }
                return@blockingIntent
            }

            widthString.toIntOrNull() == null -> return@blockingIntent
        }
        val width = widthString.toInt()
        if (width !in 0..100) return@blockingIntent
        reduce { state.copy(width = width) }
    }

    private fun doneClicked() = intent {
        addKnittingUseCase(
            generateKnitting(
                width = state.width ?: 0,
                height = state.height ?: 0,
                pattern = state.loops,
                name = state.name
            )
        )
        postSideEffect(PopBackStack)
    }
}