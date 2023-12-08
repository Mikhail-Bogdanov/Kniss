package com.qwertyuiop.white.ui.utilsUI.navigation

import com.qwertyuiop.white.ui.composables.destinations.AcceptingDestination
import com.qwertyuiop.white.ui.composables.destinations.MainDestination
import com.qwertyuiop.white.ui.composables.destinations.SettingsDestination
import com.qwertyuiop.white.ui.composables.destinations.StartDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object NavGraphs {
    val WhiteNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            StartDestination,
            MainDestination,
            SettingsDestination,
            AcceptingDestination
        ).associateBy { it.route }
        override val route: String = "white"
        override val startRoute: Route = AcceptingDestination //TODO: SET ACCORDING TO DESIGN
    }
}