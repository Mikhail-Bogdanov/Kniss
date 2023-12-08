package com.qwertyuiop.white.ui.composables.accepting.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.qwertyuiop.white.R
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptPolicyClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.AcceptTermsClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.PolicyLinkClicked
import com.qwertyuiop.white.ui.composables.accepting.mvi.AcceptingEvent.TermsLinkClicked


enum class AcceptingType(
    @StringRes val titleId: Int,
    val onClickEvent: AcceptingEvent,
    val onChangeEvent: AcceptingEvent
) {
    Terms(
        R.string.terms_of_use,
        TermsLinkClicked,
        AcceptTermsClicked
    ),
    Policy(
        R.string.privacy_policy,
        PolicyLinkClicked,
        AcceptPolicyClicked
    );

    val title
        @ReadOnlyComposable
        @Composable
        get() = stringResource(id = titleId)
}