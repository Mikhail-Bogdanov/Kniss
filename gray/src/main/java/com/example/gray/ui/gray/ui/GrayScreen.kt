package com.example.gray.ui.gray.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.gray.ui.gray.mvi.GrayEvent
import com.example.gray.ui.gray.mvi.GrayState
import com.example.gray.utils.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun GrayScreen(
    state: GrayState,
    onEvent: (GrayEvent) -> Unit
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
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
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        GrayScreenContent(
            paddingValues = paddingValues,
            snackbarState = snackBarHostState,
            grayState = state,
            onEvent = onEvent
        )
    }
}