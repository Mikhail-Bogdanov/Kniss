package com.qwertyuiop.gray.utils.navigation

import com.qwertyuiop.gray.ui.destinations.ErrorHostDestination
import com.qwertyuiop.gray.ui.destinations.GrayHostDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val GrayNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            GrayHostDestination,
            ErrorHostDestination
        ).associateBy { it.route }

        override val route: String = "gray"

        override val startRoute: Route = GrayHostDestination
    }
}
