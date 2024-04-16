package com.evoteam.presentation.ui.composables.presentation.knitting

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
import com.evoteam.core.navigation.Transitions
import com.evoteam.domain.entities.Knitting
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.RowUndoneButtonClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingSideEffect.PopBackStack
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingViewModel
import com.evoteam.presentation.ui.composables.presentation.knitting.ui.KnittingEndDialog
import com.evoteam.presentation.ui.composables.presentation.knitting.ui.KnittingIndicator
import com.evoteam.presentation.ui.composables.presentation.knitting.ui.KnittingRowDoneButton
import com.evoteam.presentation.ui.composables.presentation.knitting.ui.KnittingTopBar
import com.evoteam.presentation.ui.utils.composables.PrimaryButton
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
    knitting: Knitting
) {
    val viewModel = getViewModel<KnittingViewModel> {
        parametersOf(knitting)
    }
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            KnittingTopBar(state = state, onEvent = viewModel::dispatch)
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

            KnittingIndicator(
                state = state,
                onEvent = viewModel::dispatch
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryButton(
                    text = stringResource(R.string.undo_row),
                    enabled = state.currentRow != 0,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
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
        }
    }
}