package com.example.frontendinternship.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TopBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

        Text(text = "WELCOME", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
        Text(text = "TIME:7:45", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
    }
}
