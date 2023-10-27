package com.example.gray.ui.gray

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appdestinations.AppDestinations
import com.example.appdestinations.AppDestinations.Error
import com.example.gray.ui.generalComponents.navigate
import com.example.gray.ui.gray.mvi.GrayEvent
import com.example.gray.ui.gray.mvi.GraySideEffect
import com.example.gray.ui.gray.mvi.GraySideEffect.NavigateToError
import com.example.gray.ui.gray.mvi.GraySideEffect.RequestPermissions
import com.example.gray.ui.gray.mvi.GraySideEffect.ShowSnackbar
import com.example.gray.ui.gray.mvi.GrayState
import com.example.gray.ui.gray.mvi.GrayViewModel
import com.example.gray.ui.gray.ui.GrayScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addGrayScreen(
    navController: NavController
) {
    composable(AppDestinations.Gray) {
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
            handleSideEffect(
                sideEffect,
                navController,
                scope,
                snackbarHostState,
                launcher,
                state
            )
        }
    }
}

private fun handleSideEffect(
    sideEffect: GraySideEffect,
    navController: NavController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    state: GrayState
) {
    when (sideEffect) {
        is NavigateToError -> navController.navigate(Error, sideEffect.message)
        is ShowSnackbar -> scope.launch { snackbarHostState.showSnackbar(sideEffect.message) }
        RequestPermissions -> {
            if (SDK_INT >= TIRAMISU && !state.isGranted)
                launcher.launch(POST_NOTIFICATIONS)
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