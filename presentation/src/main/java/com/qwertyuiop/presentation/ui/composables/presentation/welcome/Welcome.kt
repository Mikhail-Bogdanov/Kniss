package com.qwertyuiop.presentation.ui.composables.presentation.welcome

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
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.presentation.ui.composables.destinations.StartDestination
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeTopBar
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.ContinueContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.EditContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.LanguageContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.RemoveContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.SizeContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.StampContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.TrackContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi.WelcomeContent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent
import com.qwertyuiop.presentation.ui.utils.extensions.navigateClear
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination(style = Transitions::class)
@Composable
fun Welcome(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<WelcomeViewModel>()
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            WelcomeTopBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 24.dp)
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
                GreetingContent.Language -> LanguageContent(viewModel::dispatch)
                GreetingContent.Welcome -> WelcomeContent(viewModel::dispatch)
                GreetingContent.Size -> SizeContent(viewModel::dispatch)
                GreetingContent.Stamp -> StampContent(viewModel::dispatch)
                GreetingContent.Remove -> RemoveContent(viewModel::dispatch)
                GreetingContent.Track -> TrackContent(viewModel::dispatch)
                GreetingContent.Edit -> EditContent(viewModel::dispatch)
                GreetingContent.Continue -> ContinueContent(viewModel::dispatch)
            }
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToStart -> navigator.navigateClear(StartDestination)
        }
    }
}