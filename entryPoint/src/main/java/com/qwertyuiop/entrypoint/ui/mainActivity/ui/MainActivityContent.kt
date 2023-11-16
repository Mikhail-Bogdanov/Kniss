package com.qwertyuiop.entrypoint.ui.mainActivity.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.qwertyuiop.appdestinations.AppDestinations
import com.qwertyuiop.gray.ui.error.addErrorScreen
import com.qwertyuiop.gray.ui.gray.addGrayScreen
import com.qwertyuiop.white.ui.composables.host.addWhiteScreen
import com.qwertyuiop.white.ui.composables.loading.addLoadingScreen

@Composable
fun MainActivityContent() {
    val appNavController = rememberNavController()

    NavHost(
        navController = appNavController,
        startDestination = AppDestinations.White //TODO SET TO LOADING
    ) {
        addLoadingScreen(appNavController)
        addWhiteScreen()
        addErrorScreen(appNavController)
        addGrayScreen(appNavController)
    }
}