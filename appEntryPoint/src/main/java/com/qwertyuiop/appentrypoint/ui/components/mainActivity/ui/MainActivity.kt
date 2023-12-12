package com.qwertyuiop.appentrypoint.ui.components.mainActivity.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.qwertyuiop.appentrypoint.ui.components.mainActivity.mvi.MainActivityViewModel
import com.qwertyuiop.appentrypoint.ui.components.mainActivity.navigation.NavGraphs
import com.qwertyuiop.appentrypoint.ui.theme.MainAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel = koinViewModel()
            val state = viewModel.collectAsState().value

            MainAppTheme(
                darkTheme = state.darkTheme
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    engine = rememberAnimatedNavHostEngine()
                )
            }
        }
    }
}