package com.ex.gray.ui.error.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ex.gray.ui.error.mvi.ErrorEvent
import com.ex.gray.ui.error.mvi.ErrorEvent.UpdateRequest
import com.ex.gray.ui.error.mvi.ErrorEvent.UpdateSslRequest
import com.ex.gray.utils.Constants
import com.ex.gray.utils.Constants.retry

@Composable
fun ErrorScreenContent(
    paddingValues: PaddingValues,
    errorMsg: String?,
    onEvent: (ErrorEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val errorText = errorMsg ?: Constants.unknownError

        Text(
            text = errorText,
            modifier = Modifier
                .fillMaxWidth(0.8f),
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            onClick = {
                onEvent(
                    if (errorMsg == Constants.sslError) {
                        UpdateSslRequest
                    } else {
                        UpdateRequest
                    }
                )
            },
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = retry,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
