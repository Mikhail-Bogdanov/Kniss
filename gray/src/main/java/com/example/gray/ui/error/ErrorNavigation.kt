package com.example.gray.ui.error

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.appdestinations.AppDestinations
import com.example.gray.ui.error.mvi.ErrorSideEffect
import com.example.gray.ui.error.mvi.ErrorViewModel
import com.example.gray.ui.error.ui.ErrorScreen
import com.example.gray.ui.generalComponents.composableArguments
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addErrorScreen(
    navController: NavController
) {
    composableArguments(AppDestinations.Error) { argument ->
        val viewModel: ErrorViewModel = koinViewModel()

        argument?.let { ErrorScreen(message = it, onEvent = viewModel::dispatch) }

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(
    sideEffect: ErrorSideEffect,
    navController: NavController
) {
    when (sideEffect) {
        ErrorSideEffect.NavigateToGray -> navController.navigate(AppDestinations.Gray)
    }
}