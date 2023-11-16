package com.qwertyuiop.white.ui.composables.host

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qwertyuiop.appdestinations.AppDestinations.White
import com.qwertyuiop.white.ui.composables.host.mvi.HostSideEffect
import com.qwertyuiop.white.ui.composables.host.mvi.HostSideEffect.OpenPolicy
import com.qwertyuiop.white.ui.composables.host.mvi.HostSideEffect.OpenTerms
import com.qwertyuiop.white.ui.composables.host.mvi.HostViewModel
import com.qwertyuiop.white.ui.composables.host.ui.HostScreen
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.PolicyLink
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.TermsLink
import com.qwertyuiop.white.ui.utilsUI.UtilsFunctions.openChromeTab
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
        OpenPolicy -> openChromeTab(context, PolicyLink)
        OpenTerms -> openChromeTab(context, TermsLink)
    }
}