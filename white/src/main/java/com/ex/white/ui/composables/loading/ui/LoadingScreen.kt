package com.ex.white.ui.composables.loading.ui

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
import com.ex.white.R
import com.ex.white.ui.composables.loading.mvi.LoadingEvent
import com.ex.white.ui.theme.MainAppTheme

@Composable
fun LoadingScreen(onEvent: (LoadingEvent) -> Unit) {
    LaunchedEffect(key1 = Unit) {
//        onEvent(LoadingEvent.GetRequest) //TODO GET REQUEST
    }

    MainAppTheme {
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
}