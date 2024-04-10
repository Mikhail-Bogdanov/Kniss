package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.MenuButtonClicked
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton

@Composable
fun KnittingEndDialog(onEvent: (KnittingEvent) -> Unit) = Dialog(
    onDismissRequest = {},
    properties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(R.string.end_congratulations),
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center
            ),
            color = MaterialTheme.colorScheme.onSecondary
        )

        PrimaryButton(text = stringResource(R.string.menu)) {
            onEvent(MenuButtonClicked)
        }
    }
}