package com.qwertyuiop.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object Transitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
        slideInHorizontally(
            animationSpec = spring(
                stiffness = Spring.StiffnessMediumLow
            )
        ) { it.times(2) }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
        slideOutHorizontally(
            animationSpec = spring(
                stiffness = Spring.StiffnessMediumLow
            )
        ) { -it }
}