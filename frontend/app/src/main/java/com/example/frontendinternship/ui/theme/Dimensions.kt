package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val viewTiny: Dp = 20.dp,
    val viewNormal: Dp = 40.dp,
    val viewBig: Dp = 60.dp,
    val viewLarge: Dp = 80.dp,
    val viewLargePlus: Dp = 90.dp,
    val clipTiny: Dp = 8.dp,
    val clipSmall: Dp = 12.dp,
    val clipNormal: Dp = 16.dp,
)
val LocalDimensions = compositionLocalOf { Dimensions() }
