package com.ex.white.ui.composables.settings

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ex.white.R
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect.OpenMail
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPlayMarket
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPolicy
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect.OpenTerms
import com.ex.white.ui.composables.settings.mvi.SettingsSideEffect.PopBackStack
import com.ex.white.ui.composables.settings.mvi.SettingsViewModel
import com.ex.white.ui.composables.settings.ui.SettingsScreen
import com.ex.white.ui.utilsUI.ConstantsUI.PolicyLink
import com.ex.white.ui.utilsUI.ConstantsUI.TermsLink
import com.ex.white.ui.utilsUI.UtilsFunctions.openChromeTab
import com.ex.white.ui.utilsUI.UtilsFunctions.openGmail
import com.ex.white.ui.utilsUI.UtilsFunctions.openPlayMarket
import com.ex.white.ui.utilsUI.WhiteDestinations.Companion.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.addSettingsScreen(
    navController: NavController
) {
    composable(Settings) {
        val viewModel: SettingsViewModel = koinViewModel()
        val state = viewModel.collectAsState().value

        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        val snackBarHostState = remember {
            SnackbarHostState()
        }

        SettingsScreen(snackBarHostState, viewModel::dispatch, state)

        viewModel.collectSideEffect { sideEffect ->
            handleSideEffect(
                sideEffect,
                navController,
                context,
                snackBarHostState,
                coroutineScope
            )
        }
    }
}

private fun handleSideEffect(
    sideEffect: SettingsSideEffect,
    navController: NavController,
    context: Context,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    when (sideEffect) {
        OpenPolicy -> openChromeTab(context, PolicyLink)
        OpenTerms -> openChromeTab(context, TermsLink)
        OpenMail -> openGmail(
            context
        ) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(context.getString(R.string.unable_to_start_mail_app))
            }
        }

        OpenPlayMarket -> openPlayMarket(context)
        PopBackStack -> navController.popBackStack()
    }
}