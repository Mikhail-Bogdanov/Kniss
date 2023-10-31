package com.ex.white.ui.composables.start

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ex.white.ui.composables.start.mvi.StartSideEffect
import com.ex.white.ui.composables.start.mvi.StartSideEffect.NavigateToMain
import com.ex.white.ui.composables.start.mvi.StartSideEffect.NavigateToSettings
import com.ex.white.ui.composables.start.mvi.StartViewModel
import com.ex.white.ui.composables.start.ui.StartScreen
import com.ex.white.ui.utilsUI.WhiteDestinations.Companion.Main
import com.ex.white.ui.utilsUI.WhiteDestinations.Companion.Settings
import com.ex.white.ui.utilsUI.WhiteDestinations.Companion.Start
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addStartScreen(
    navController: NavController
) {
    composable(Start) {
        val viewModel: StartViewModel = koinViewModel()
        val state = viewModel.collectAsState().value

        StartScreen(state, viewModel::dispatch)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(
    sideEffect: StartSideEffect,
    navController: NavController
) {
    when (sideEffect) {
        NavigateToMain -> navController.navigate(Main)
        NavigateToSettings -> navController.navigate(Settings)
    }
}