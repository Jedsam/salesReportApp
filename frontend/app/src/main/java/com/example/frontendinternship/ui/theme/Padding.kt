package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val Tiny: Dp = 8.dp,
    val Small: Dp = 16.dp,
    val Normal: Dp = 24.dp,
    val Big: Dp = 32.dp,
    val Large: Dp = 40.dp,
)

val LocalPadding = compositionLocalOf { Padding() }