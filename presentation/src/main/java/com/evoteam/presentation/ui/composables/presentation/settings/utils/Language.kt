package com.evoteam.presentation.ui.composables.presentation.settings.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R

enum class Language(
    @StringRes private val titleResource: Int,
    @StringRes private val untranslatableTitleResource: Int,
    val tag: String?
) {
    Default(
        R.string.system_default,
        R.string.system_default_untransl,
        null
    ),
    English(
        R.string.english,
        R.string.english_untransl,
        "en"
    ),
    Russian(
        R.string.russian,
        R.string.russian_untransl,
        "ru"
    );

    val title
        @Composable
        get() = stringResource(id = titleResource)

    val untranslatableTitle
        @Composable
        get() = stringResource(id = untranslatableTitleResource)

    companion object {
        fun getByTag(tag: String) = Language.entries.find { it.tag == tag } ?: Default
    }
}