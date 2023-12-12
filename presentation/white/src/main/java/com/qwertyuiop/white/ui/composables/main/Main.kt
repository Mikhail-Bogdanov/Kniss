package com.qwertyuiop.white.ui.composables.main

import androidx.compose.runtime.Composable
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.white.ui.composables.destinations.SettingsDestination
import com.qwertyuiop.white.ui.composables.main.mvi.MainSideEffect.NavigateToSettings
import com.qwertyuiop.white.ui.composables.main.mvi.MainViewModel
import com.qwertyuiop.white.ui.composables.main.ui.MainScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Main(
    navigator: DestinationsNavigator
) {
    val viewModel: MainViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    MainScreen(state = state, viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToSettings -> navigator.navigate(SettingsDestination)
        }
    }
}