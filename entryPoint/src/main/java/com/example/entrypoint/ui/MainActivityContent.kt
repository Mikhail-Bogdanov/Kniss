package com.example.entrypoint.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.appdestinations.AppDestinations
import com.example.gray.ui.error.addErrorScreen
import com.example.gray.ui.gray.addGrayScreen
import com.example.white.ui.composables.host.addWhiteScreen
import com.example.white.ui.composables.loading.addLoadingScreen

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