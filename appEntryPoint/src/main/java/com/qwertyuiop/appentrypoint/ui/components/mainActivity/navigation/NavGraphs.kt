package com.qwertyuiop.appentrypoint.ui.components.mainActivity.navigation

import com.qwertyuiop.gray.utils.navigation.NavGraphs.GrayNavGraph
import com.qwertyuiop.utils.navigation.NavGraphs.EntryPointNavGraph
import com.qwertyuiop.white.ui.utilsUI.navigation.NavGraphs.WhiteNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraphs {
    val root = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
        override val route: String = "root"
        override val startRoute: Route = WhiteNavGraph //TODO: SET TO EntryPointNavGraph
        override val nestedNavGraphs: List<NavGraphSpec> = listOf(
            WhiteNavGraph,
            GrayNavGraph,
            EntryPointNavGraph
        )
    }
}