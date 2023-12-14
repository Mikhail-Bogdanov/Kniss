package com.qwertyuiop.white.ui.composables.settings.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat.forLanguageTags
import com.qwertyuiop.white.R

enum class Language(
    private val localeTag: String?,
    @StringRes private val titleId: Int
) {
    Default(null, R.string.system_default),
    English("en", R.string.english),
    Turkish("tr", R.string.turkish),
    Spanish("es", R.string.spanish),
    Portuguese("pt", R.string.portuguese),
    Ukrainian("uk", R.string.ukrainian),
    Russian("ru", R.string.russian);

    val localeList get() = forLanguageTags(localeTag)

    val title
        @Composable
        @ReadOnlyComposable
        get() = stringResource(titleId)
}