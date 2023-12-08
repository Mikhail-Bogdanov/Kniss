package com.qwertyuiop.white.ui.composables.settings

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.qwertyuiop.white.R
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenMail
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPlayMarket
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPolicy
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenTerms
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.PopBackStack
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsViewModel
import com.qwertyuiop.white.ui.composables.settings.ui.SettingsScreen
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.PolicyLink
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.TermsLink
import com.qwertyuiop.white.ui.utilsUI.UtilsFunctions.openChromeTab
import com.qwertyuiop.white.ui.utilsUI.UtilsFunctions.openGmail
import com.qwertyuiop.white.ui.utilsUI.UtilsFunctions.openPlayMarket
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination
@Composable
fun Settings(
    navigator: DestinationsNavigator
) {
    val viewModel: SettingsViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    SettingsScreen(snackBarHostState, viewModel::dispatch, state)

    viewModel.collectSideEffect { sideEffect ->
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
            PopBackStack -> navigator.popBackStack()
        }
    }
}