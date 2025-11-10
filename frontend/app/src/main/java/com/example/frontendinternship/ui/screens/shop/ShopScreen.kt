package com.example.frontendinternship.ui.screens.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.RoundedTextField
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.WifiOnorOff
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun ShopScreen(
    navController: NavController,
    viewModel: ShopViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MyScaffold(
        navController = navController,
        containerColor = MaterialTheme.colorScheme.background,
        topBarRightSideContent = {
            WifiOnorOff(isOn = true)
        },
        screenText = "Shop",
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimensions.current.viewMax),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                RoundedButton(
                    buttonText = "Register",
                    onButtonPress = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(LocalPadding.current.Big))
            RoundedTextField(
                textFieldInformation = "Full Name",
                textValue = uiState.currentUser.username,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundedTextField(
                textFieldInformation = "Business Name",
                textValue = uiState.currentUser.username,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundedTextField(
                textFieldInformation = "Phone",
                textValue = uiState.currentUser.username,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundedTextField(
                textFieldInformation = "Business Address",
                textValue = uiState.currentUser.username,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundedTextField(
                textFieldInformation = "Username",
                textValue = uiState.currentUser.username,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            RoundedTextField(
                textFieldInformation = "Password",
                textValue = "*".repeat(uiState.currentUser.password.length),
                onFieldValueChange = {},
                keyboardType = KeyboardType.Decimal,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.Big))
        }
    }
}

@Preview
@Composable
fun ShopScreenPreview() {
    FrontendInternshipTheme {
        ShopScreen(
            navController = rememberNavController(),
        )
    }
}

