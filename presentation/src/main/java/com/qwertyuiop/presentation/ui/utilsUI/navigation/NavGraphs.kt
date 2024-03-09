package com.qwertyuiop.presentation.ui.utilsUI.navigation

import com.qwertyuiop.presentation.ui.composables.destinations.PresentationDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object NavGraphs {
    val PresentationNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            PresentationDestination
        ).associateBy { it.route }
        override val route: String = "presentation_root"
        override val startRoute: Route = PresentationDestination
    }
}