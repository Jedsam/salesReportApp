package com.example.frontendinternship.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.ui.navigation.Screen

@Composable
fun NavigationItem(
    text: String = "Home",
    onClick: () -> Unit = {},
    icon: ImageVector = Icons.Sharp.Home,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = "NavigationItem",
            tint = Color.Gray
        )
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .clickable(
                    onClick = onClick
                )
        )
    }
}

@Preview
@Composable
fun NavigationItemPreview() {
    NavigationItem()
}
