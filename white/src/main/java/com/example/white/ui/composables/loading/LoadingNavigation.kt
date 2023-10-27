package com.example.white.ui.composables.loading

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appdestinations.AppDestinations
import com.example.appdestinations.AppDestinations.Gray
import com.example.appdestinations.AppDestinations.White
import com.example.core.extensions.navigateClear
import com.example.white.ui.composables.loading.mvi.LoadingSideEffect
import com.example.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToGray
import com.example.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToWhite
import com.example.white.ui.composables.loading.mvi.LoadingViewModel
import com.example.white.ui.composables.loading.ui.LoadingScreen
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addLoadingScreen(
    navController: NavController
) {
    composable(AppDestinations.Loading) {
        val viewModel: LoadingViewModel = koinViewModel()

        LoadingScreen(onEvent = viewModel::dispatch)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(
    sideEffect: LoadingSideEffect,
    navController: NavController
) {
    when (sideEffect) {
        NavigateToGray -> navController.navigateClear(Gray)
        NavigateToWhite -> navController.navigateClear(White)
    }
}