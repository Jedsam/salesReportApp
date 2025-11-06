package com.example.frontendinternship.ui.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.frontendinternship.ui.components.TopBarWithReturn
import com.example.frontendinternship.ui.common.viewmodel.ProductTransferViewModel
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun ProductAddScreen(
    navController: NavController,
    viewModel: ProductAddViewModel = hiltViewModel(),
    productTransferViewModel: ProductTransferViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val sharedState by productTransferViewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithReturn(
                navController = navController,
                currentScreenText = "Add Product",
                isConnected = true,
            )
        },
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
                        productTransferViewModel.chooseAddOperation()
                        navController.navigate(Screen.Catalog.route) {
                            popUpTo(Screen.Catalog.route) {
                                inclusive = true
                            }
                        }
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
                        navController.navigate(Screen.Catalog.route) {
                            popUpTo(Screen.Catalog.route) {
                                inclusive = true
                            }
                        }
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
                textValue = sharedState.currentProduct.productName,
                onFieldValueChange = {},
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
                    textValue = String.format("%.2f", sharedState.currentProduct.price),
                    onFieldValueChange = {},
                    keyboardType = KeyboardType.Decimal,
                    textFieldModifier = Modifier.fillMaxWidth(0.45f),
                    textColor = Color.Gray,
                )
                RoundedTextField(
                    textFieldInformation = "VAT Rate (%)",
                    textValue = sharedState.currentProduct.vatRate.toString(),
                    onFieldValueChange = {},
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
    val productTransferViewModel = remember { mutableStateOf(ProductTransferViewModel()) }
    productTransferViewModel.value.updateProduct(
        ProductModel(
            productName = "MyProduct1",
            vatRate = 10.0,
            price = 30.0
        )
    )
    FrontendInternshipTheme {
        ProductAddScreen(
            navController = rememberNavController(),
            productTransferViewModel = productTransferViewModel.value
        )
    }
}

