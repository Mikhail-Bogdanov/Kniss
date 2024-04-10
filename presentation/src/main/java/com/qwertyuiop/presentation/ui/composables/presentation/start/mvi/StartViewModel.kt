@file:OptIn(OrbitExperimental::class)

package com.qwertyuiop.presentation.ui.composables.presentation.start.mvi

import android.annotation.SuppressLint
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.presentation.ui.composables.presentation.shared.KnittingPatternState
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.AddLoopClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.AddRowClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.DoneClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.HeightInput
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.RemoveLoopClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.WidthInput
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartSideEffect.NavigateToKnitting
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartSideEffect.NavigateToSettings
import com.qwertyuiop.presentation.ui.utils.extensions.extend
import com.qwertyuiop.presentation.ui.utils.extensions.extendInner
import com.qwertyuiop.presentation.ui.utils.extensions.isBlankOrEmpty
import com.qwertyuiop.presentation.ui.utils.extensions.removeWherever
import com.qwertyuiop.presentation.ui.utils.extensions.toLoopDTO
import com.qwertyuiop.presentation.ui.utils.extensions.updateInnerItem
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class StartViewModel(
    private val vibrator: Vibrator
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
        }
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
        val widthInt = state.width ?: 0 //it won't happen
        val heightInt = state.height ?: 0 //it won't happen
        postSideEffect(
            NavigateToKnitting(
                KnittingPatternState(
                    width = widthInt,
                    height = heightInt,
                    pattern = state.loops.map { it.map { it.toLoopDTO() } }
                )
            )
        )
    }
}