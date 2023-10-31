package com.ex.core.extensions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ex.core.utils.CoreConstants.NavArgument

fun NavGraphBuilder.composableArguments(
    destination: String,
    content: @Composable (String?) -> Unit
) {
    composable(
        route = "$destination?$NavArgument={$NavArgument}",
        arguments = listOf(
            navArgument(NavArgument) {
                NavType.StringType
            }
        )
    ) { entry ->
        content(entry.arguments?.getString(NavArgument))
    }
}

fun NavController.navigate(
    dest: String,
    arg: String
) {
    navigate(
        "$dest?$NavArgument=$arg"
    )
}

fun NavController.navigateClear(
    dest: String
) {
    navigate(dest) {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}