package com.qwertyuiop.entrypoint.ui.mainActivity.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.qwertyuiop.entrypoint.ui.mainActivity.mvi.MainActivityViewModel
import com.qwertyuiop.entrypoint.ui.theme.MainAppTheme
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel = koinViewModel()
            val state = viewModel.collectAsState().value

            MainAppTheme(
                darkTheme = state.darkTheme
            ) {
                MainActivityContent()
            }
        }
    }
}