package com.example.frontendinternship.ui.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PaymentSwapButton(
    onClick: () -> Unit = {},
    fillMaxWidthFraction: Float = 1f,
    text: String = "Credit",
    isActive: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fillMaxWidthFraction)
            .fillMaxHeight(0.85f)
            .clickable { onClick() }
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isActive)
                    MaterialTheme.colorScheme.surface
                else
                    Color.LightGray
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isActive)
                MaterialTheme.colorScheme.primary
            else
               Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}