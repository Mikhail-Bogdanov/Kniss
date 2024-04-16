package com.evoteam.presentation.ui.utils.navigation

import com.evoteam.presentation.ui.composables.destinations.KnittingDestination
import com.evoteam.presentation.ui.composables.destinations.MenuDestination
import com.evoteam.presentation.ui.composables.destinations.SettingsDestination
import com.evoteam.presentation.ui.composables.destinations.StartDestination
import com.evoteam.presentation.ui.composables.destinations.WelcomeDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object NavGraphs {
    val PresentationNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            StartDestination,
            KnittingDestination,
            SettingsDestination,
            WelcomeDestination,
            MenuDestination
        ).associateBy { it.route }
        override val route: String = "presentation_root"
        override val startRoute: Route = WelcomeDestination
    }
}