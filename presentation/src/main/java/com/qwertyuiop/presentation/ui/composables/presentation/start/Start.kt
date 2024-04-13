package com.qwertyuiop.presentation.ui.composables.presentation.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.presentation.ui.composables.destinations.SettingsDestination
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartSideEffect.NavigateToSettings
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartSideEffect.PopBackStack
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.start.ui.StartScreen
import com.qwertyuiop.presentation.ui.composables.presentation.start.ui.StartTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Start(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<StartViewModel>()
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StartTopBar(onEvent = viewModel::dispatch)
        }
    ) { paddingValues ->
        StartScreen(
            paddingValues,
            state,
            viewModel::dispatch
        )
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToSettings -> navigator.navigate(SettingsDestination)
            PopBackStack -> navigator.popBackStack()
        }
    }
}