package com.qwertyuiop.presentation.ui.composables.presentation.settings.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R

enum class Language(
    @StringRes private val titleResource: Int,
    val tag: String?
) {
    Default(R.string.system_default, null),
    English(R.string.english, "en"),
    Russian(R.string.russian, "ru");

    val title
        @Composable
        get() = stringResource(id = titleResource)

    companion object {
        fun getByTag(tag: String) = Language.entries.find { it.tag == tag } ?: Default
    }
}