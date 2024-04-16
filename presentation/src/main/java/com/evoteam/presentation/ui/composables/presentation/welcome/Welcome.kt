package com.evoteam.presentation.ui.composables.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.evoteam.core.navigation.Transitions
import com.evoteam.presentation.ui.composables.destinations.MenuDestination
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.NavigateToMenu
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.PopBackStack
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeViewModel
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeTopBar
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.ContinueContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.EditContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.LanguageContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.PropertiesContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.RemoveContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.StampContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.TrackContent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.WelcomeContent
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Continue
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Edit
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Language
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Properties
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Remove
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Stamp
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Track
import com.evoteam.presentation.ui.composables.presentation.welcome.utils.GreetingContent.Welcome
import com.evoteam.presentation.ui.utils.extensions.navigateClear
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Welcome(
    navigator: DestinationsNavigator,
    isWatchingAgain: Boolean = false
) {
    val viewModel = getViewModel<WelcomeViewModel> {
        parametersOf(isWatchingAgain)
    }
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            WelcomeTopBar(state, viewModel::dispatch)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .aspectRatio(1f),
                color = MaterialTheme.colorScheme.onBackground,
                trackColor = MaterialTheme.colorScheme.background,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round
            )
            else when (state.currentGreetingContent) {
                Language -> LanguageContent(viewModel::dispatch)
                Welcome -> WelcomeContent(viewModel::dispatch)
                Properties -> PropertiesContent(viewModel::dispatch)
                Stamp -> StampContent(viewModel::dispatch)
                Remove -> RemoveContent(viewModel::dispatch)
                Track -> TrackContent(viewModel::dispatch)
                Edit -> EditContent(state, viewModel::dispatch)
                Continue -> ContinueContent(viewModel::dispatch)
            }
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToMenu -> navigator.navigateClear(MenuDestination)
            PopBackStack -> navigator.popBackStack()
        }
    }
}