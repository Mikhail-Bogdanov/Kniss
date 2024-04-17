package com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeNextButton
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.evoteam.presentation.ui.utils.extensions.fillScreenWidth

@Composable
fun StampContent(onEvent: (WelcomeEvent) -> Unit) {
    WelcomeScreenText(
        text = stringResource(R.string.make_pattern),
        minLines = 3
    )
    Image(
        painter = painterResource(id = R.drawable.stamp_help_image),
        contentDescription = null,
        modifier = Modifier
            .fillScreenWidth()
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.FillWidth
    )
    WelcomeNextButton(onEvent = onEvent)
}