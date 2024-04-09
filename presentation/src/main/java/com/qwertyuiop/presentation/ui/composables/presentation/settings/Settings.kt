package com.qwertyuiop.presentation.ui.composables.presentation.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsSideEffect.PopBackStack
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.settings.ui.SettingsScreen
import com.qwertyuiop.presentation.ui.composables.presentation.settings.ui.SettingsTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Settings(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<SettingsViewModel>()
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SettingsTopBar(state = state, onEvent = viewModel::dispatch)
        }
    ) { paddingValues ->
        SettingsScreen(
            paddingValues,
            viewModel::dispatch
        )
    }

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            PopBackStack -> navigator.popBackStack()
        }
    }
}