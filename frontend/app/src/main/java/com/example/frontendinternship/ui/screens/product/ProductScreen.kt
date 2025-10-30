package com.example.frontendinternship.ui.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.RoundedTextField
import com.example.frontendinternship.ui.components.TopBarWithReturn
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithReturn(
                navController = navController,
                screenToGoBackTo = Screen.Catalog,
                currentScreenText = "Products",
                isConnected = true,
            )
        },
        bottomBar = {}) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RoundedTextField(
                textValue = uiState.currentProduct.productName,
                onFieldValueChange = {},
                keyboardType = KeyboardType.Text
            )
            Row() {
                RoundedTextField(
                    textValue = uiState.currentProduct.price.toString(),
                    onFieldValueChange = {},
                    keyboardType = KeyboardType.Decimal
                )
                RoundedTextField(
                    textValue = uiState.currentProduct.vatRate.toString(),
                    onFieldValueChange = {},
                    keyboardType = KeyboardType.Number
                )
            }
            RoundedButton(
                buttonText = "Save",
                onButtonPress = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalPadding.current.Small)
                    .height(LocalDimensions.current.viewBig),
                borderColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.primary,
            )
            RoundedButton(
                buttonText = "Delete",
                onButtonPress = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalPadding.current.Small)
                    .height(LocalDimensions.current.viewBig),
                        borderColor = Color.Red,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = Color.Red
            )
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    FrontendInternshipTheme {
        ProductScreen(navController = rememberNavController())
    }
}

