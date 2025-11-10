package com.example.frontendinternship.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.sharp.Wifi
import androidx.compose.material.icons.sharp.WifiOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TopBarWithComponent(
    onNavigationBarClick: () -> Unit,
    currentScreenText: String,
    color: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimensions.current.viewBigPlus),
        ) {
            Text(
                text = currentScreenText,
                fontSize = LocalTextFormat.current.sizeMain,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalPadding.current.Small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { onNavigationBarClick()},
                    modifier = Modifier
                        .size(
                            width = LocalDimensions.current.viewLarge,
                            height = LocalDimensions.current.viewBig
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color,
                        contentColor = Color.Gray,
                        disabledContainerColor = color,
                        disabledContentColor = color,
                    ),
                    contentPadding = PaddingValues(
                        horizontal = LocalPadding.current.VeryTiny,
                        vertical = LocalPadding.current.Mini
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "Arrow",
                        tint = Color.Gray
                    )
                }
                content()
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun TopBarWithComponentPreviewWifiOn() {
    FrontendInternshipTheme {
        TopBarWithComponent(
            onNavigationBarClick = {},
            currentScreenText = "TopBar",
            content = {
                    Icon(
                        imageVector = Icons.Sharp.Wifi,
                        contentDescription = "WifiOn",
                        tint = Color.Gray
                    )
            },
        )
    }
}

@Preview
@Composable
fun TopBarWithComponentPreviewWifiOff() {
    FrontendInternshipTheme {
        TopBarWithComponent(
            onNavigationBarClick = {},
            currentScreenText = "TopBar",
            content = {
                Icon(
                    imageVector = Icons.Sharp.WifiOff,
                    contentDescription = "WifiOff",
                    tint = Color.Gray
                )
            },
        )
    }
}

@Preview
@Composable
fun TopBarWithComponentPreviewSync() {
    FrontendInternshipTheme {
        TopBarWithComponent(
            onNavigationBarClick = {},
            currentScreenText = "TopBar",
            content = {
                Button(
                    onClick = {
                        // if (isLoggedIn) {
                        // onSyncButtonPressed()
                        // } else {
                        // onLoginButtonPressed()
                        // }
                    },
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
        )
    }
}
@Preview
@Composable
fun TopBarWithComponentPreviewLogin() {
    FrontendInternshipTheme {
        TopBarWithComponent(
            onNavigationBarClick = {},
            currentScreenText = "TopBar",
            content = {
                Button(
                    onClick = {
                        // if (isLoggedIn) {
                        // onSyncButtonPressed()
                        // } else {
                        // onLoginButtonPressed()
                        // }
                    },
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
                        text = "Login",
                        fontSize = LocalTextFormat.current.sizeBig,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }
}
