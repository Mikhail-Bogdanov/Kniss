package com.evoteam.presentation.ui.composables.presentation.menu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuEvent.SettingsClicked
import com.evoteam.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun MenuTopBar(onEvent: (MenuEvent) -> Unit) = GeneralTopAppBar(
    title = stringResource(id = R.string.menu),
    navigationIcon = {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp),
            contentScale = ContentScale.FillBounds
        )
    },
    actions = {
        IconButton(
            onClick = {
                onEvent(SettingsClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null
            )
        }
    }
)