package com.qwertyuiop.presentation.ui.composables.presentation.start.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.DoneClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.HeightInput
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.StampQuestionCloseRequested
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.StampQuestionMarkClicked
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartEvent.WidthInput
import com.qwertyuiop.presentation.ui.composables.presentation.start.mvi.StartState
import com.qwertyuiop.presentation.ui.utils.composables.LoopTypeDropdownMenu
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton

@Composable
fun StartScreen(
    paddingValues: PaddingValues,
    state: StartState,
    onEvent: (StartEvent) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(top = 24.dp)
        .padding(horizontal = 24.dp),
    verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    StartScreenTextField(
        value = state.width,
        onValueChange = { newWidth ->
            onEvent(WidthInput(newWidth))
        },
        placeholderText = stringResource(R.string.width),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        )
    )

    StartScreenTextField(
        value = state.height,
        onValueChange = { newHeight ->
            onEvent(HeightInput(newHeight))
        },
        placeholderText = stringResource(R.string.height),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            onEvent(DoneClicked)
        }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.stamp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
        )
        IconButton(
            onClick = {
                onEvent(StampQuestionMarkClicked)
            },
            modifier = Modifier
                .size(16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Icon(
                imageVector = Icons.Default.QuestionMark,
                contentDescription = null,
                modifier = Modifier
                    .size(12.dp)
            )
        }
        LoopTypeDropdownMenu(
            expanded = state.stampQuestionOpened,
            onDismissRequest = {
                onEvent(StampQuestionCloseRequested)
            }
        ) {
            Text(
                text = stringResource(R.string.stamp_description),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }
    }

    StartStamp(state, onEvent)

    PrimaryButton(
        text = stringResource(R.string.done),
        enabled = state.isCorrect,
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .padding(vertical = 8.dp)
    ) {
        onEvent(DoneClicked)
    }
}