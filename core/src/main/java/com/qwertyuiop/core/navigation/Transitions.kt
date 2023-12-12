package com.qwertyuiop.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object Transitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
        slideInVertically(
            animationSpec = spring(dampingRatio = 0.35f)
        ) { it }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = fadeOut()
}