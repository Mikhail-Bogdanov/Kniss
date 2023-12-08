package com.qwertyuiop.gray.ui.gray.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent
import com.qwertyuiop.gray.ui.gray.mvi.GrayState
import com.qwertyuiop.gray.utils.Constants

@Composable
fun GrayScreen(
    state: GrayState,
    onEvent: (GrayEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val controller = rememberSystemUiController()

    SideEffect {
        controller.setSystemBarsColor(
            color = Constants.BarsColor,
            darkIcons = true,
            isNavigationBarContrastEnforced = false
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        GrayScreenContent(
            paddingValues = paddingValues,
            grayState = state,
            onEvent = onEvent
        )
    }
}