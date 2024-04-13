package com.qwertyuiop.presentation.ui.composables.presentation.menu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.domain.entities.Knitting

@Composable
fun MenuKnittingItem(
    knitting: Knitting,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .padding(2.dp)
        .background(MaterialTheme.colorScheme.secondary)
        .clickable(onClick = onItemClick),
    contentAlignment = Alignment.TopEnd
) {
    IconButton(
        onClick = onRemoveClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ),
        modifier = Modifier
            .size(36.dp)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = knitting.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = stringResource(R.string.current_row_menu_item, knitting.currentRow.plus(1)),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.width_menu_item, knitting.width),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = stringResource(R.string.height_menu_item, knitting.height),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}