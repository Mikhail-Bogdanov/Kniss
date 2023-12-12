package com.qwertyuiop.entrypoint

import androidx.compose.runtime.Composable
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.entrypoint.mvi.LoadingSideEffect.NavigateToGray
import com.qwertyuiop.entrypoint.mvi.LoadingSideEffect.NavigateToWhite
import com.qwertyuiop.entrypoint.mvi.LoadingViewModel
import com.qwertyuiop.entrypoint.ui.LoadingScreen
import com.qwertyuiop.gray.utils.navigation.NavGraphs.GrayNavGraph
import com.qwertyuiop.white.ui.utilsUI.navigation.NavGraphs.WhiteNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun EntryPointHost(
    navigator: DestinationsNavigator
) {
    val viewModel: LoadingViewModel = koinViewModel()

    LoadingScreen(onEvent = viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToGray -> navigator.navigateClear(GrayNavGraph)
            NavigateToWhite -> navigator.navigateClear(WhiteNavGraph)
        }
    }
}