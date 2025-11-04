package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val viewMini: Dp = 10.dp,
    val viewTiny: Dp = 20.dp,
    val viewSmall: Dp = 30.dp,
    val viewNormal: Dp = 40.dp,
    val viewNormalPlus: Dp = 50.dp,
    val viewBig: Dp = 60.dp,
    val viewBigPlus: Dp = 70.dp,
    val viewLarge: Dp = 80.dp,
    val viewLargePlus: Dp = 90.dp,
    val viewMax: Dp = 100.dp,
    val viewExtrasMini: Dp = 110.dp,
    val viewExtrasTiny: Dp = 120.dp,
    val viewExtrasSmall: Dp = 130.dp,
    val viewExtrasNormal: Dp = 140.dp,
    val viewExtrasNormalPlus: Dp = 150.dp,
    val viewExtrasBig: Dp = 160.dp,
    val viewExtrasBigPlus: Dp = 170.dp,
    val viewExtrasLarge: Dp = 180.dp,
    val viewExtrasLargePlus: Dp = 190.dp,
    val viewExtrasMax: Dp = 100.dp,
    val clipTiny: Dp = 8.dp,
    val clipSmall: Dp = 12.dp,
    val clipNormal: Dp = 16.dp,
)
val LocalDimensions = compositionLocalOf { Dimensions() }
