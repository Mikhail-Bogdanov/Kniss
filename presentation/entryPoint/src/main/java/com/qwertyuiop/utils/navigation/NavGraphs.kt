package com.qwertyuiop.utils.navigation

import com.qwertyuiop.entrypoint.destinations.EntryPointHostDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val EntryPointNavGraph = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
            EntryPointHostDestination
        ).associateBy { it.route }

        override val route: String = "entryPoint"

        override val startRoute: Route = EntryPointHostDestination
    }
}