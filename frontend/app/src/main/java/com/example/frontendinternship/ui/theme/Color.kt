package com.example.frontendinternship.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val blueEastern: Color = Color(color = 0xFFCFE2F3),
    val blueCerulean: Color = Color(color = 0xFF0DB2EE),
    val blue: Color = Color(color = 0xFF09899F),
    val darkGreen: Color = Color(color = 0xFF07792D),
    val lightGray: Color = Color(color = 0xFFD0CDCD),

)

val LocalColors = compositionLocalOf { Colors() }
