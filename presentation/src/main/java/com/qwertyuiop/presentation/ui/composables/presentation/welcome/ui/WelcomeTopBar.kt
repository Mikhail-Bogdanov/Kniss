package com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun WelcomeTopBar() = GeneralTopAppBar(
    title = stringResource(id = R.string.app_name),
    navigationIcon = {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp),
            contentScale = ContentScale.FillBounds
        )
    }
)