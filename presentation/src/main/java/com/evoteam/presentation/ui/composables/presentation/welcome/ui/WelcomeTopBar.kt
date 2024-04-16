package com.evoteam.presentation.ui.composables.presentation.welcome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.BackButtonClicked
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeState
import com.evoteam.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun WelcomeTopBar(state: WelcomeState, onEvent: (WelcomeEvent) -> Unit) = GeneralTopAppBar(
    title = stringResource(id = R.string.app_name),
    navigationIcon = {
        when (state.isWatchingAgain) {
            true -> IconButton(
                onClick = {
                    onEvent(BackButtonClicked)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }

            false -> Image(
                imageVector = ImageVector.vectorResource(R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
)