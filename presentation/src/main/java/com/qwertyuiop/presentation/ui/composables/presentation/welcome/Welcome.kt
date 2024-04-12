package com.qwertyuiop.presentation.ui.composables.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.core.navigation.Transitions
import com.qwertyuiop.presentation.ui.composables.destinations.StartDestination
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.ContinueClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.LanguageSelected
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeNextButton
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryInputField
import com.qwertyuiop.presentation.ui.utils.extensions.fillScreenWidth
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

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 24.dp)
                .padding(vertical = 24.dp)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.remainingGreetingContent.firstOrNull()) {
                GreetingContent.Language -> {
                    WelcomeScreenText(text = stringResource(R.string.select_language))
                    Language.entries.forEach { language ->
                        PrimaryButton(text = language.title) {
                            viewModel.dispatch(LanguageSelected(language))
                        }
                    }
                }

                GreetingContent.Welcome -> {
                    WelcomeScreenText(text = stringResource(R.string.app_welcome_desc))
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.app_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .fillScreenWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Size -> {
                    WelcomeScreenText(text = stringResource(R.string.choose_size))
                    PrimaryInputField(
                        value = "",
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier
                            .fillScreenWidth(),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.width),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )
                    PrimaryInputField(
                        value = "",
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier
                            .fillScreenWidth(),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.height),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Stamp -> {
                    WelcomeScreenText(text = stringResource(R.string.make_pattern))
                    Image(
                        painter = painterResource(id = R.drawable.stamp_help_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillScreenWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Remove -> {
                    WelcomeScreenText(text = stringResource(R.string.remove_loop_help_text))
                    Image(
                        painter = painterResource(id = R.drawable.stamp_help_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillScreenWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Track -> {
                    WelcomeScreenText(text = stringResource(R.string.track_progress_text))
                    Image(
                        painter = painterResource(id = R.drawable.track_help_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillScreenWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Edit -> {
                    WelcomeScreenText(text = stringResource(R.string.edit_help_text))
                    Image(
                        painter = painterResource(id = R.drawable.edit_help_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillScreenWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    WelcomeNextButton(onEvent = viewModel::dispatch)
                }

                GreetingContent.Continue -> {
                    WelcomeScreenText(text = stringResource(R.string.try_now))
                    PrimaryButton(
                        text = stringResource(id = R.string.continue_use),
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        viewModel.dispatch(ContinueClicked)
                    }
                }

                null -> {} //nothing
            }
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            NavigateToStart -> navigator.navigateClear(StartDestination)
        }
    }
}