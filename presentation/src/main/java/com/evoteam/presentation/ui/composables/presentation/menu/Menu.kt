package com.evoteam.presentation.ui.composables.presentation.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.ui.composables.destinations.KnittingDestination
import com.evoteam.presentation.ui.composables.destinations.SettingsDestination
import com.evoteam.presentation.ui.composables.destinations.StartDestination
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.AddKnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.KnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.RemoveKnittingClicked
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToKnitting
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToSettings
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuSideEffect.NavigateToStart
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuViewModel
import com.evoteam.presentation.ui.composables.presentation.menu.ui.MenuFAB
import com.evoteam.presentation.ui.composables.presentation.menu.ui.MenuKnittingItem
import com.evoteam.presentation.ui.composables.presentation.menu.ui.MenuTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination
@Composable
fun Menu(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<MenuViewModel>()
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MenuTopBar(viewModel::dispatch)
        },
        floatingActionButton = {
            MenuFAB {
                viewModel.dispatch(AddKnittingClicked)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = state.knittings,
                key = { it.id }
            ) { knitting ->
                MenuKnittingItem(
                    knitting = knitting,
                    onItemClick = { viewModel.dispatch(KnittingClicked(knitting)) },
                    onRemoveClick = { viewModel.dispatch(RemoveKnittingClicked(knitting)) }
                )
            }
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NavigateToKnitting -> navigator.navigate(KnittingDestination(sideEffect.knitting))
            NavigateToSettings -> navigator.navigate(SettingsDestination)
            NavigateToStart -> navigator.navigate(StartDestination)
        }
    }
}