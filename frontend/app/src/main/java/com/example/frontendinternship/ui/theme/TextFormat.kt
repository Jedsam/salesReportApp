package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

data class TextFormat(
    val sizeTiny: TextUnit = 8.sp,
    val sizeSmall: TextUnit = 12.sp,
    val sizeNormal: TextUnit = 16.sp,
    val sizeBig: TextUnit = 20.sp,
    val sizeLarge: TextUnit = 24.sp,
)

val LocalTextFormat = compositionLocalOf { TextFormat() }
