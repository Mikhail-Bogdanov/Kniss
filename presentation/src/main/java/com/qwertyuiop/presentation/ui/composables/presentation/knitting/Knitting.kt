package com.qwertyuiop.presentation.ui.composables.presentation.knitting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.presentation.ui.composables.destinations.StartDestination
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowUndoneButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.PopBackStack
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui.KnittingEndDialog
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui.KnittingIndicator
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui.KnittingRowDoneButton
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui.KnittingTopBar
import com.qwertyuiop.presentation.ui.composables.presentation.shared.KnittingPatternState
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton
import com.qwertyuiop.presentation.ui.utils.extensions.navigateClear
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Knitting(
    navigator: DestinationsNavigator,
    knittingPatternState: KnittingPatternState
) {
    val viewModel = getViewModel<KnittingViewModel> {
        parametersOf(knittingPatternState)
    }
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            KnittingTopBar(onEvent = viewModel::dispatch)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = state.isEndDialogShowing,
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 100
                    )
                )
            ) {
                KnittingEndDialog(onEvent = viewModel::dispatch)
            }

            KnittingIndicator(state = state)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryButton(
                    text = stringResource(R.string.undo_row),
                    enabled = state.currentRow != 0
                ) {
                    viewModel.dispatch(RowUndoneButtonClicked)
                }

                KnittingRowDoneButton(
                    state = state,
                    onEvent = viewModel::dispatch
                )
            }
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            PopBackStack -> navigator.popBackStack()
            NavigateToStart -> navigator.navigateClear(StartDestination)
        }
    }
}