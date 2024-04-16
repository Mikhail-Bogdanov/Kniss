package com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeNextButton
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.evoteam.presentation.ui.utils.extensions.fillScreenWidth

@Composable
fun WelcomeContent(onEvent: (WelcomeEvent) -> Unit) {
    WelcomeScreenText(text = stringResource(R.string.app_welcome_desc))
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.app_icon),
        contentDescription = null,
        modifier = Modifier
            .fillScreenWidth(),
        contentScale = ContentScale.FillWidth
    )
    WelcomeNextButton(onEvent = onEvent)
}