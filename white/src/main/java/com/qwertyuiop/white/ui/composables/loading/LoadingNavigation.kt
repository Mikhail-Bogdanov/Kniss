package com.qwertyuiop.white.ui.composables.loading

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qwertyuiop.appdestinations.AppDestinations
import com.qwertyuiop.appdestinations.AppDestinations.Gray
import com.qwertyuiop.appdestinations.AppDestinations.White
import com.qwertyuiop.core.extensions.navigateClear
import com.qwertyuiop.white.ui.composables.loading.mvi.LoadingSideEffect
import com.qwertyuiop.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToGray
import com.qwertyuiop.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToWhite
import com.qwertyuiop.white.ui.composables.loading.mvi.LoadingViewModel
import com.qwertyuiop.white.ui.composables.loading.ui.LoadingScreen
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