package com.qwertyuiop.presentation.ui.utils.navigation

import com.qwertyuiop.presentation.ui.composables.destinations.StartDestination
import com.qwertyuiop.presentation.ui.composables.destinations.KnittingDestination
import com.qwertyuiop.presentation.ui.composables.destinations.SettingsDestination
import com.qwertyuiop.presentation.ui.composables.destinations.WelcomeDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object NavGraphs {
    val PresentationNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            StartDestination,
            KnittingDestination,
            SettingsDestination,
            WelcomeDestination
        ).associateBy { it.route }
        override val route: String = "presentation_root"
        override val startRoute: Route = WelcomeDestination
    }
}