package com.qwertyuiop.white.ui.composables.host.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.qwertyuiop.white.R
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.AcceptPolicyClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.AcceptTermsClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.PolicyLinkClicked
import com.qwertyuiop.white.ui.composables.host.mvi.HostEvent.TermsLinkClicked

enum class DialogType(
    @StringRes val titleId: Int,
    val onClickEvent: HostEvent,
    val onChangeEvent: HostEvent
) {
    Terms(R.string.terms_of_use, TermsLinkClicked, AcceptTermsClicked),
    Policy(R.string.privacy_policy, PolicyLinkClicked, AcceptPolicyClicked);

    val title
        @ReadOnlyComposable
        @Composable
        get() = stringResource(id = this.titleId)
}