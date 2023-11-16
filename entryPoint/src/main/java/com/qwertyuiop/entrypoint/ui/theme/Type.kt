package com.qwertyuiop.entrypoint.ui.theme

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
import com.qwertyuiop.entrypoint.R

val Inter = FontFamily(
    Font(R.font.inter_100, W100),
    Font(R.font.inter_200, W200),
    Font(R.font.inter_300, W300),
    Font(R.font.inter_400, W400),
    Font(R.font.inter_500, W500),
    Font(R.font.inter_600, W600),
    Font(R.font.inter_700, W700),
    Font(R.font.inter_800, W800),
    Font(R.font.inter_900, W900)
)

val Nunito = FontFamily(
    Font(R.font.nunito_200, W200),
    Font(R.font.nunito_300, W300),
    Font(R.font.nunito_400, W400),
    Font(R.font.nunito_500, W500),
    Font(R.font.nunito_600, W600),
    Font(R.font.nunito_700, W700),
    Font(R.font.nunito_800, W800),
    Font(R.font.nunito_900, W900)
)

val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 16.sp,
        lineHeight = 18.sp
    )
)