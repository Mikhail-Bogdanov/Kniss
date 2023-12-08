package com.qwertyuiop.white.ui.composables.accepting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.qwertyuiop.white.R
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.DoneClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingState
import com.qwertyuiop.white.ui.composables.accepting.utils.AcceptingType
import com.qwertyuiop.white.ui.composables.accepting.utils.AcceptingType.entries

@Composable
fun AcceptingScreen(
    state: AcceptingState,
    onEvent: (AcceptingEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!state.showAccepting) return

        entries.forEach { type ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = type.title,
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable { onEvent(type.onClickEvent) }
                )

                Checkbox(
                    checked = when (type) {
                        AcceptingType.Policy -> state.policyAccepted
                        AcceptingType.Terms -> state.termsAccepted
                    },
                    onCheckedChange = { onEvent(type.onChangeEvent) },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = MaterialTheme.colorScheme.onBackground,
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
        Button(
            onClick = {
                onEvent(DoneClicked)
            },
            enabled = state.termsAccepted && state.policyAccepted,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.continue_use),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}