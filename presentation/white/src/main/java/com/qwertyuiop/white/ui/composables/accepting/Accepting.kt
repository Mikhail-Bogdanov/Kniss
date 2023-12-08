package com.qwertyuiop.white.ui.composables.accepting

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingSideEffect.*
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingViewModel
import com.qwertyuiop.white.ui.composables.accepting.ui.AcceptingScreen
import com.qwertyuiop.white.ui.composables.destinations.StartDestination
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.PolicyLink
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.TermsLink
import com.qwertyuiop.white.ui.utilsUI.UtilsFunctions.openChromeTab
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination
@Composable
fun Accepting(
    navigator: DestinationsNavigator,
    context: Context = LocalContext.current
) {
    val viewModel: AcceptingViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    AcceptingScreen(state = state, onEvent = viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            OpenPolicy -> openChromeTab(context, PolicyLink)
            OpenTerms -> openChromeTab(context, TermsLink)
            NavigateToStart -> navigator.navigateClear(StartDestination)
        }
    }
}