package com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeNextButton
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.qwertyuiop.presentation.ui.utils.extensions.fillScreenWidth

@Composable
fun EditContent(onEvent: (WelcomeEvent) -> Unit) {
    WelcomeScreenText(text = stringResource(R.string.edit_help_text))
    Image(
        painter = painterResource(id = R.drawable.edit_help_image),
        contentDescription = null,
        modifier = Modifier
            .fillScreenWidth(),
        contentScale = ContentScale.FillWidth
    )
    WelcomeNextButton(onEvent = onEvent)
}