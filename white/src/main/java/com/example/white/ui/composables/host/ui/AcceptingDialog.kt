package com.example.white.ui.composables.host.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.window.DialogProperties
import com.example.white.R
import com.example.white.ui.composables.host.mvi.HostEvent
import com.example.white.ui.composables.host.mvi.HostEvent.AcceptPolicyClicked
import com.example.white.ui.composables.host.mvi.HostEvent.AcceptTermsClicked
import com.example.white.ui.composables.host.mvi.HostEvent.AcceptingDialogDoneClicked
import com.example.white.ui.composables.host.mvi.HostEvent.PolicyLinkClicked
import com.example.white.ui.composables.host.mvi.HostEvent.TermsLinkClicked
import com.example.white.ui.composables.host.mvi.HostState

@Composable
fun AcceptingDialog(state: HostState, onEvent: (HostEvent) -> Unit) {
    if (!state.acceptingDialogShowing) return
    AlertDialog(
        onDismissRequest = {}, //this dialog can't be dismissed
        confirmButton = {
            Button(
                onClick = {
                    onEvent(AcceptingDialogDoneClicked)
                },
                enabled = state.termsAccepted && state.policyAccepted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(
                    text = stringResource(R.string.continue_use),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = stringResource(id = R.string.privacy_policy),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable {
                                onEvent(PolicyLinkClicked)
                            }
                    )
                    Checkbox(
                        checked = state.policyAccepted,
                        onCheckedChange = {
                            onEvent(AcceptPolicyClicked)
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = MaterialTheme.colorScheme.primary,
                            checkedColor = MaterialTheme.colorScheme.background,
                            uncheckedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = stringResource(id = R.string.terms_of_use),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable {
                                onEvent(TermsLinkClicked)
                            }
                    )
                    Checkbox(
                        checked = state.termsAccepted,
                        onCheckedChange = {
                            onEvent(AcceptTermsClicked)
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = MaterialTheme.colorScheme.primary,
                            checkedColor = MaterialTheme.colorScheme.background,
                            uncheckedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        containerColor = MaterialTheme.colorScheme.background,
        textContentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp
    )
}