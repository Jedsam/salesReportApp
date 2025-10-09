package com.example.frontendinternship.ui.common.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class TriangleCutCornerShape(private val cutSizeDp: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cutSize = with(density) { cutSizeDp.dp.toPx() }

        return Outline.Generic(
            path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width - cutSize, 0f)
                lineTo(size.width, cutSize)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
        )
    }
}
