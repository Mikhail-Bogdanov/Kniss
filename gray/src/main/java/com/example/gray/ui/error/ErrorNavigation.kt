package com.example.gray.ui.error

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.appdestinations.AppDestinations
import com.example.gray.ui.error.mvi.ErrorSideEffect
import com.example.gray.ui.error.mvi.ErrorViewModel
import com.example.gray.ui.error.ui.ErrorScreen
import com.example.gray.ui.generalComponents.composableArguments
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addErrorScreen(
    navController: NavController
) {
    composableArguments(AppDestinations.Error) { argument ->
        val viewModel: ErrorViewModel = koinViewModel()

        val snackbarHostState = remember {
            SnackbarHostState()
        }

        argument?.let {
            ErrorScreen(
                message = it,
                onEvent = viewModel::dispatch,
                snackbarHostState = snackbarHostState
            )
        }

        val scope = rememberCoroutineScope()

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController, scope, snackbarHostState)
        }
    }
}

private fun handleSideEffect(
    sideEffect: ErrorSideEffect,
    navController: NavController,
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    when (sideEffect) {
        ErrorSideEffect.NavigateToGray -> navController.navigate(AppDestinations.Gray)
        is ErrorSideEffect.ShowSnackBar -> scope.launch {
            snackBarHostState.showSnackbar(sideEffect.message)
        }
    }
}