package com.example.frontendinternship.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Wifi
import androidx.compose.material.icons.sharp.WifiOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TopBarWithSync(
    currentScreenText: String,
    isConnected: Boolean,
    onSyncButtonPressed: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = currentScreenText,
            modifier = Modifier.align(Alignment.Center),
            fontSize = LocalTextFormat.current.sizeLarge,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalPadding.current.Small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isConnected) {
                Icon(
                    imageVector = Icons.Sharp.Wifi,
                    contentDescription = "WifiOn",
                    tint = Color.Gray
                )
            } else {
                Icon(
                    imageVector = Icons.Sharp.WifiOff,
                    contentDescription = "WifiOff",
                    tint = Color.Gray
                )
            }
            Button(
                onClick = { onSyncButtonPressed() },
                modifier = Modifier
                    .size(
                        width = LocalDimensions.current.viewBig,
                        height = LocalDimensions.current.viewBig
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.background,
                ),
                contentPadding = PaddingValues(
                    horizontal = LocalPadding.current.VeryTiny,
                    vertical = LocalPadding.current.Mini
                )
            ) {
                Text(
                    text = "Sync",
                    fontSize = LocalTextFormat.current.sizeBig,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun TopBarWithSyncPreviewWifiOn() {
    FrontendInternshipTheme {
        TopBarWithSync(
            onSyncButtonPressed = {},
            currentScreenText = "Main Screen",
            isConnected = true
        )
    }
}

@Preview
@Composable
fun TopBarWithSyncPreviewWifiOff() {
    FrontendInternshipTheme {
        TopBarWithSync(
            onSyncButtonPressed = {},
            currentScreenText = "Main Screen",
            isConnected = false
        )
    }
}
