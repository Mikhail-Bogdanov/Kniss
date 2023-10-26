package com.example.gray.ui.gray

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appdestinations.AppDestinations
import com.example.appdestinations.AppDestinations.Error
import com.example.gray.ui.generalComponents.navigate
import com.example.gray.ui.gray.mvi.GraySideEffect
import com.example.gray.ui.gray.mvi.GrayViewModel
import com.example.gray.ui.gray.ui.GrayScreen
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addGrayScreen(
    navController: NavController
) {
    composable(AppDestinations.Gray) {
        val viewModel: GrayViewModel = koinViewModel()
        val state = viewModel.collectAsState().value

        GrayScreen(state = state, onEvent = viewModel::dispatch)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(
    sideEffect: GraySideEffect,
    navController: NavController
) {
    when (sideEffect) {
        is GraySideEffect.NavigateToError -> navController.navigate(
            Error,
            sideEffect.message
        )
    }
}