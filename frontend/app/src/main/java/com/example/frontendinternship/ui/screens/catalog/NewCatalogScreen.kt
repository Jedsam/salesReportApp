package com.example.frontendinternship.ui.screens.catalog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.ui.components.ProductList
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.TopBarWithSync
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun NewCatalogScreen(
    navController: NavController,
    viewModel: NewCatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            TopBarWithSync(
                onSyncButtonPressed = {},
                isConnected = true,
                currentScreenText = "Products"
            )
        },
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(LocalPadding.current.Small)
            ) {
                RoundedButton(
                    buttonText = "Go to Basket (0)",
                    onButtonPress = {},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = Color.Red,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Red
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBasket,
                        contentDescription = "AddIcon",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                RoundedButton(
                    buttonText = "+",
                    textSize = LocalTextFormat.current.sizeLarge,
                    onButtonPress = {},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primary,
                ) { }
            }
        },
    ) { innerPadding ->
        ProductList(
            onProductSelected = {},
            productList = uiState.productList,
            paddingValue = innerPadding
        )
    }
}


@Preview
@Composable
fun NewCatalogScreenPreview() {
    FrontendInternshipTheme {
        NewCatalogScreen(
            navController = rememberNavController(),
            viewModel = NewCatalogViewModel_Factory.newInstance(
                FakeLoadProductsUseCase()
            )
        )
    }
}

class FakeLoadProductsUseCase : ILoadProductsUseCase {
    override suspend fun invoke(): List<Product> {
        return listOf(
            Product(productName = "MyProduct1", vatRate = 10, price = 30.0f),
            Product(productName = "MyProduct2", vatRate = 15, price = 12.3450f),
            Product(productName = "MyProduct3", vatRate = 18, price = 30.40f),
            Product(
                productName = "MyBigProductNameItsBigItsVeryBig",
                vatRate = 0,
                price = 15.25f
            ),
            Product(productName = "mp2", vatRate = 20, price = 0.00f),
            Product(productName = "$*^($@!*@#", vatRate = 5, price = 5.49812940f),
            Product(productName = "m", vatRate = 20, price = 0.00f),
            Product(
                productName = "油売ってる自動販売機に重要な機材の欠片",
                vatRate = 5,
                price = 5.49812940f
            ),
            Product(
                productName = "DenemDenemDenemDenemeeeeDeneme",
                vatRate = 5,
                price = 5.49812940f
            ),
        )
    }
}
