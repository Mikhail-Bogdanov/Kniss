package com.example.gray.ui.generalComponents

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gray.utils.Constants.NavArgument

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