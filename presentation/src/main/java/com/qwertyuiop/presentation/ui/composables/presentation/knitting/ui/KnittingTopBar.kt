package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.*
import com.qwertyuiop.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun KnittingTopBar(onEvent: (KnittingEvent) -> Unit) = GeneralTopAppBar(
    title = stringResource(id = R.string.app_name),
    navigationIcon = {
        IconButton(
            onClick = {
                onEvent(BackButtonClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
)