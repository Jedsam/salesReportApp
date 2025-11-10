package com.example.frontendinternship.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Wifi
import androidx.compose.material.icons.sharp.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun WifiOnorOff(
    isOn: Boolean
) {
    if (isOn) {
        Icon(
            imageVector = Icons.Sharp.Wifi,
            contentDescription = "WifiOn",
            tint = Color.Gray
        )
    }
    else {
        Icon(
            imageVector = Icons.Sharp.WifiOff,
            contentDescription = "WifiOff",
            tint = Color.Gray
        )
    }

}