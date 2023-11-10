package com.qwertyuiop.white.ui.composables.host.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent
import com.qwertyuiop.white.ui.composables.host.mvi.HostState
import com.qwertyuiop.white.ui.composables.main.addMainScreen
import com.qwertyuiop.white.ui.composables.settings.addSettingsScreen
import com.qwertyuiop.white.ui.composables.start.addStartScreen
import com.qwertyuiop.white.ui.theme.MainAppTheme
import com.qwertyuiop.white.ui.utilsUI.WhiteDestinations

@Composable
fun HostScreen(
    state: HostState,
    onEvent: (HostEvent) -> Unit
) {
    LockScreenOrientation(SCREEN_ORIENTATION_PORTRAIT)

    val navController = rememberNavController()

    MainAppTheme(
        darkTheme = state.darkTheme
    ) {
        AcceptingDialog(state = state, onEvent = onEvent)

        NavHost(
            navController = navController,
            startDestination = WhiteDestinations.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            addStartScreen(navController)
            addMainScreen(navController)
            addSettingsScreen(navController)
        }
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}