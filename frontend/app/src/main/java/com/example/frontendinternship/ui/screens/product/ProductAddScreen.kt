package com.example.frontendinternship.ui.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.RoundedTextField
import com.example.frontendinternship.ui.common.viewmodel.ProductViewModel
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.WifiOnorOff
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun ProductAddScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MyScaffold (
        navController = navController,
        containerColor = MaterialTheme.colorScheme.background,
        topBarRightSideContent = {
            WifiOnorOff(isOn = true)
        },
        screenText = "Add Product",
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimensions.current.viewExtrasLargePlus),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                RoundedButton(
                    buttonText = "Save",
                    onButtonPress = {
                        viewModel.chooseAddOperation()
                        navController.popBackStack(
                            Screen.Catalog.route,
                            inclusive = false
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primary,
                )
                RoundedButton(
                    buttonText = "Cancel",
                    onButtonPress = {
                        navController.popBackStack(
                            Screen.Catalog.route,
                            inclusive = false
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = Color.Red,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Red
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .height(LocalDimensions.current.viewBig)
            )
            RoundedTextField(
                textFieldInformation = "Product Name",
                textValue = uiState.currentProduct.productName,
                onFieldValueChange = { newText ->
                    if (newText.length < 20)
                        viewModel.updateProductName(newText)
                },
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.9f),
                textColor = Color.Gray,
            )
            Spacer(
                modifier = Modifier
                    .height(LocalDimensions.current.viewTiny)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = LocalPadding.current.Normal)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)
            ) {
                RoundedTextField(
                    textFieldInformation = "Price",
                    textValue = String.format("%.2f", uiState.currentProduct.price),
                    onFieldValueChange = { newText ->
                        if (newText.length < 20)
                            viewModel.updatePrice(newText)
                    },
                    keyboardType = KeyboardType.Decimal,
                    textFieldModifier = Modifier.fillMaxWidth(0.45f),
                    textColor = Color.Gray,
                )
                RoundedTextField(
                    textFieldInformation = "VAT Rate (%)",
                    textValue = uiState.currentProduct.vatRate.toString(),
                    onFieldValueChange = { newText ->
                        if (newText.length < 20)
                            viewModel.updateVatRate(newText)
                    },
                    keyboardType = KeyboardType.Number,
                    textFieldModifier = Modifier.fillMaxWidth(),
                    textColor = Color.Gray,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductAddScreenPreview() {
    val productViewModel = remember { mutableStateOf(ProductViewModel()) }
    productViewModel.value.updateProduct(
        ProductModel(
            productName = "MyProduct1",
            vatRate = 10.0,
            price = 30.0
        )
    )
    FrontendInternshipTheme {
        ProductAddScreen(
            navController = rememberNavController(),
            viewModel = productViewModel.value
        )
    }
}

