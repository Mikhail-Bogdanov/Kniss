package com.qwertyuiop.core.extensions

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction


fun DestinationsNavigator.navigateClear(
    dest: Direction
) {
    navigate(dest) {
        popUpTo("root") {
            inclusive = true
        }
    }
}