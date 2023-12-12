package com.qwertyuiop.white.ui.composables.start

import androidx.compose.runtime.Composable
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.white.ui.composables.destinations.MainDestination
import com.qwertyuiop.white.ui.composables.destinations.SettingsDestination
import com.qwertyuiop.white.ui.composables.start.mvi.StartSideEffect.NavigateToMain
import com.qwertyuiop.white.ui.composables.start.mvi.StartSideEffect.NavigateToSettings
import com.qwertyuiop.white.ui.composables.start.mvi.StartViewModel
import com.qwertyuiop.white.ui.composables.start.ui.StartScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Start(
    navigator: DestinationsNavigator
) {
    val viewModel: StartViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    StartScreen(state, viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToMain -> navigator.navigate(MainDestination)
            NavigateToSettings -> navigator.navigate(SettingsDestination)
        }
    }
}