package com.example.white.ui.composables.host

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appdestinations.AppDestinations.White
import com.example.white.ui.composables.host.mvi.HostSideEffect
import com.example.white.ui.composables.host.mvi.HostSideEffect.OpenPolicy
import com.example.white.ui.composables.host.mvi.HostSideEffect.OpenTerms
import com.example.white.ui.composables.host.mvi.HostViewModel
import com.example.white.ui.composables.host.ui.HostScreen
import com.example.white.ui.utilsUI.NavigationArguments.Policy
import com.example.white.ui.utilsUI.NavigationArguments.Terms
import com.example.white.ui.utilsUI.UtilsFunctions.openWebView
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addWhiteScreen() {
    composable(White) {
        val viewModel: HostViewModel = koinViewModel()
        val state = viewModel.collectAsState().value

        val context = LocalContext.current

        HostScreen(state = state, onEvent = viewModel::dispatch)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(sideEffect, context)
        }
    }
}

private fun handleSideEffect(
    sideEffect: HostSideEffect,
    context: Context
) {
    when (sideEffect) {
        OpenPolicy -> openWebView(context, Policy)
        OpenTerms -> openWebView(context, Terms)
    }
}