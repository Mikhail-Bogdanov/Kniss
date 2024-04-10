package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.BackButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.EndEditingButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.HelpMenuDismissRequest
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.StartEditingButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingState
import com.qwertyuiop.presentation.ui.utils.composables.GeneralTopAppBar
import com.qwertyuiop.presentation.ui.utils.composables.LoopTypeDropdownMenu

@Composable
fun KnittingTopBar(state: KnittingState, onEvent: (KnittingEvent) -> Unit) = GeneralTopAppBar(
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
    },
    actions = {
        IconButton(
            onClick = {
                onEvent(HelpButtonClicked)
            },
            modifier = Modifier
                .padding(end = 2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = null
            )
        }
        LoopTypeDropdownMenu(expanded = state.isHelpMenuOpened) {
            onEvent(HelpMenuDismissRequest)
        }
        when (state.isInEdit) {
            true -> IconButton(
                onClick = {
                    onEvent(EndEditingButtonClicked)
                },
                modifier = Modifier
                    .padding(start = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null
                )
            }

            false -> IconButton(
                onClick = {
                    onEvent(StartEditingButtonClicked)
                },
                modifier = Modifier
                    .padding(start = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
        }
    }
)