package com.evoteam.appentrypoint.ui.components.mainActivity.navigation

import com.evoteam.presentation.ui.utils.navigation.NavGraphs.PresentationNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val root = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
        override val route: String = "root"
        override val startRoute: Route = PresentationNavGraph
        override val nestedNavGraphs: List<NavGraphSpec> = listOf(
            PresentationNavGraph
        )
    }
}