package com.evoteam.appentrypoint.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.W100
import androidx.compose.ui.text.font.FontWeight.Companion.W200
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.unit.sp
import com.evoteam.entrypoint.R

val SourceCodePro = FontFamily(
    Font(R.font.source_code_pro_100, W100),
    Font(R.font.source_code_pro_200, W200),
    Font(R.font.source_code_pro_300, W300),
    Font(R.font.source_code_pro_400, W400),
    Font(R.font.source_code_pro_500, W500),
    Font(R.font.source_code_pro_600, W600),
    Font(R.font.source_code_pro_700, W700),
    Font(R.font.source_code_pro_800, W800),
    Font(R.font.source_code_pro_900, W900)
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W500,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W600,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W700,
        fontSize = 18.sp,
        lineHeight = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W700,
        fontSize = 20.sp,
        lineHeight = 22.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W600,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SourceCodePro,
        fontWeight = W600,
        fontSize = 14.sp,
        lineHeight = 14.sp
    )
)