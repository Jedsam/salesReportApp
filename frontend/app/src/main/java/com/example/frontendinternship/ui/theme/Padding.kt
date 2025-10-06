package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val Tiny: Dp = 16.dp,
    val Small: Dp = 20.dp,
    val Normal: Dp = 24.dp,
    val Big: Dp = 28.dp,
    val Large: Dp = 32.dp,
)

val LocalPadding = compositionLocalOf { Padding() }