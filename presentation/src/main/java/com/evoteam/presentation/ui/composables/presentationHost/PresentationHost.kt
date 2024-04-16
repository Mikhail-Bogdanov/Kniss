package com.evoteam.presentation.ui.composables.presentationHost

import androidx.compose.runtime.Composable
import com.evoteam.core.navigation.Transitions
import com.evoteam.presentation.ui.utils.navigation.NavGraphs.PresentationNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination


@Destination(style = Transitions::class)
@Composable
fun PresentationHost() = DestinationsNavHost(navGraph = PresentationNavGraph)