package com.qwertyuiop.white.ui.composables.whitehost

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.core.utils.CoreFunctions.LockScreenOrientation
import com.qwertyuiop.white.ui.utilsUI.navigation.NavGraphs.WhiteNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination


@Destination(style = Transitions::class)
@Composable
fun WhiteHost() {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    DestinationsNavHost(navGraph = WhiteNavGraph)
}