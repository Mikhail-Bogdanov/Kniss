package com.example.gray.ui.error.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gray.ui.error.mvi.ErrorEvent
import com.example.gray.utils.Constants
import com.example.gray.utils.Constants.retry
import kotlinx.coroutines.launch

@Composable
fun ErrorScreenContent(
    paddingValues: PaddingValues,
    errorMsg: String?,
    snackBarHostState: SnackbarHostState,
    onEvent: (ErrorEvent) -> Unit
) {
    val scope = rememberCoroutineScope()

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
                if (errorMsg == Constants.sslError) {
                    onEvent(
                        ErrorEvent.UpdateSslRequest {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    Constants.SslErrorMessage
                                )
                            }
                        }
                    )
                } else {
                    onEvent(
                        ErrorEvent.UpdateRequest
                    )
                }
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
