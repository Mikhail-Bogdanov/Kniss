package com.qwertyuiop.gray.ui.gray

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.gray.ui.destinations.ErrorHostDestination
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.NavigateToError
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.RequestPermissions
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.ShowSnackbar
import com.qwertyuiop.gray.ui.gray.mvi.GrayViewModel
import com.qwertyuiop.gray.ui.gray.ui.GrayScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun GrayHost(
    navigator: DestinationsNavigator
) {
    val viewModel: GrayViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    val launcher = permissionsLauncher(viewModel::dispatch)

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    GrayScreen(
        state = state,
        onEvent = viewModel::dispatch,
        snackbarHostState = snackbarHostState
    )

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NavigateToError -> navigator.navigateClear(ErrorHostDestination(sideEffect.message))
            is ShowSnackbar -> scope.launch { snackbarHostState.showSnackbar(sideEffect.message) }
            RequestPermissions -> {
                if (SDK_INT >= TIRAMISU && !state.isGranted)
                    launcher.launch(POST_NOTIFICATIONS)
            }
        }
    }
}

@Composable
fun permissionsLauncher(
    onEvent: (GrayEvent) -> Unit
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
) { isGranted ->
    onEvent(GrayEvent.UpdatePermissionState(isGranted))
}
