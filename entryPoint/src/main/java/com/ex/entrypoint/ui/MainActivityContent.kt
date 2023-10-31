package com.ex.entrypoint.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ex.appdestinations.AppDestinations
import com.ex.gray.ui.error.addErrorScreen
import com.ex.gray.ui.gray.addGrayScreen
import com.ex.white.ui.composables.host.addWhiteScreen
import com.ex.white.ui.composables.loading.addLoadingScreen

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