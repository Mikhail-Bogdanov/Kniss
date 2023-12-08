package com.qwertyuiop.gray.ui.error

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.gray.ui.destinations.GrayHostDestination
import com.qwertyuiop.gray.ui.error.mvi.ErrorSideEffect.NavigateToGray
import com.qwertyuiop.gray.ui.error.mvi.ErrorSideEffect.ShowSnackBar
import com.qwertyuiop.gray.ui.error.mvi.ErrorViewModel
import com.qwertyuiop.gray.ui.error.ui.ErrorScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect


@Destination
@Composable
fun ErrorHost(
    navigator: DestinationsNavigator,
    errorMessage: String
) {
    val viewModel: ErrorViewModel = koinViewModel()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    ErrorScreen(
        message = errorMessage,
        onEvent = viewModel::dispatch,
        snackbarHostState = snackbarHostState
    )

    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToGray -> navigator.navigateClear(GrayHostDestination)
            is ShowSnackBar -> scope.launch {
                snackbarHostState.showSnackbar(sideEffect.message)
            }
        }
    }
}