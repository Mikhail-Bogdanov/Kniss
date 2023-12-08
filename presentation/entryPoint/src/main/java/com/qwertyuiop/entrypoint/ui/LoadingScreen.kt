package com.qwertyuiop.entrypoint.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qwertyuiop.entrypoint.mvi.LoadingEvent
import com.qwertyuiop.white.R

@Composable
fun LoadingScreen(onEvent: (LoadingEvent) -> Unit) {
    LaunchedEffect(key1 = Unit) {
        onEvent(LoadingEvent.GetRequest)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.background,
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
