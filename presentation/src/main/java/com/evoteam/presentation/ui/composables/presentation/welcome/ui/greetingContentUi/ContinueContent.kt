package com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.ContinueClicked
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.evoteam.presentation.ui.utils.composables.PrimaryButton
import com.evoteam.presentation.ui.utils.extensions.fillScreenWidth

@Composable
fun ContinueContent(onEvent: (WelcomeEvent) -> Unit) {
    WelcomeScreenText(text = stringResource(R.string.try_now))
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.app_icon),
        contentDescription = null,
        modifier = Modifier
            .fillScreenWidth(),
        contentScale = ContentScale.FillWidth
    )
    PrimaryButton(
        text = stringResource(id = R.string.continue_use),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(bottom = 72.dp)
    ) {
        onEvent(ContinueClicked)
    }
}