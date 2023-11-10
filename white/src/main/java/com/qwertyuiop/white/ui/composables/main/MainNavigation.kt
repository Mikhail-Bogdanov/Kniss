package com.qwertyuiop.white.ui.composables.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qwertyuiop.white.ui.composables.main.mvi.MainSideEffect
import com.qwertyuiop.white.ui.composables.main.mvi.MainSideEffect.NavigateToSettings
import com.qwertyuiop.white.ui.composables.main.mvi.MainViewModel
import com.qwertyuiop.white.ui.composables.main.ui.MainScreen
import com.qwertyuiop.white.ui.utilsUI.WhiteDestinations
import com.qwertyuiop.white.ui.utilsUI.WhiteDestinations.Companion.Settings
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addMainScreen(
    navController: NavController
) {
    composable(WhiteDestinations.Main) {
        val viewModel: MainViewModel = koinViewModel()
        val state = viewModel.collectAsState().value

        MainScreen(state = state, viewModel::dispatch)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(
    sideEffect: MainSideEffect,
    navController: NavController
) {
    when (sideEffect) {
        NavigateToSettings -> navController.navigate(Settings)
    }
}