package com.qwertyuiop.white.ui.composables.whiteEntryPoint

import androidx.compose.runtime.Composable
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.white.ui.composables.destinations.AcceptingDestination
import com.qwertyuiop.white.ui.composables.destinations.StartDestination
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi.WhiteEntryPointSideEffect.*
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.mvi.WhiteEntryPointViewModel
import com.qwertyuiop.white.ui.composables.whiteEntryPoint.ui.WhiteEntryPointScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun WhiteEntryPoint(
    navigator: DestinationsNavigator
) {
    val viewModel: WhiteEntryPointViewModel = koinViewModel()

    WhiteEntryPointScreen()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToAccepting -> navigator.navigateClear(AcceptingDestination)

            NavigateToStart -> navigator.navigateClear(StartDestination)
        }
    }
}