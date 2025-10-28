package com.example.frontendinternship.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun BottomBar(string: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = "SERVER: ", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
        Text(
            text = string, maxLines = 2, autoSize = TextAutoSize.StepBased(
                minFontSize = LocalTextFormat.current.sizeLarge,
                maxFontSize = LocalTextFormat.current.sizeMain,
                stepSize = 1.sp
            ), color = Color.Black
        )
    }
}
