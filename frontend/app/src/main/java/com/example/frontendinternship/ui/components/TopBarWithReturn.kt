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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.sharp.Wifi
import androidx.compose.material.icons.sharp.WifiOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TopBarWithReturn(
    navController: NavController,
    currentScreenText: String,
    isConnected: Boolean
) {
    Column() {
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
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(
                        width = LocalDimensions.current.viewLarge,
                        height = LocalDimensions.current.viewBig
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Gray,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.background,
                ),
                contentPadding = PaddingValues(
                    horizontal = LocalPadding.current.VeryTiny,
                    vertical = LocalPadding.current.Mini
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow",
                    tint = Color.Gray
                )
            }
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
fun TopBarWithReturnPreviewWifiOn() {
    FrontendInternshipTheme {
        TopBarWithReturn(
            navController = rememberNavController(),
            currentScreenText = "TopBar",
            isConnected = true
        )
    }
}

@Preview
@Composable
fun TopBarWithReturnPreviewWifiOff() {
    FrontendInternshipTheme {
        TopBarWithReturn(
            navController = rememberNavController(),
            currentScreenText = "TopBar",
            isConnected = false
        )
    }
}
