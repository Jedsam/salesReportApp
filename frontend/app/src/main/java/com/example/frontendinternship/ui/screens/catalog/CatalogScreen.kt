package com.example.frontendinternship.ui.screens.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.ui.common.viewmodel.ProductViewModel
import com.example.frontendinternship.ui.components.ProductList
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.screens.basket.BasketViewModel
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalColors
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel(),
    productViewModel: ProductViewModel,
    basketViewModel: BasketViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val productTransferUiState by productViewModel.uiState.collectAsState()
    val basketTransferUiState by basketViewModel.uiState.collectAsState()
    LaunchedEffect(productTransferUiState.productOperation) {
        viewModel.updateProductChanges(
            productTransferUiState.currentProduct,
            productTransferUiState.productOperation
        )
        productViewModel.resetProduct()
    }
    MyScaffold(
        topBarRightSideContent = {
            Button(
                onClick = {
                    if (uiState.isLoggedIn) {
                        // onSyncButtonPressed()
                    } else {
                        navController.navigate(Screen.Login.route)
                    }
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
        },
        screenText = "Catalog",
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val lastAdded = basketTransferUiState.lastAddedProduct
                Text(
                    text = buildString {
                        append(lastAdded.product.productName)
                        append(" x ")
                        append(lastAdded.count)
                        append(" : ")
                        append(lastAdded.product.price)
                        append(" | ")
                        append(lastAdded.getCost())
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.Small)
                ) {
                    RoundedButton(
                        buttonText = "Go to Basket (" + basketTransferUiState.productBasket.size + ")",
                        onButtonPress = {
                            navController.navigate(Screen.Basket.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(horizontal = LocalPadding.current.Small)
                            .height(LocalDimensions.current.viewNormalPlus),
                        borderColor = LocalColors.current.darkGreen,
                        containerColor = LocalColors.current.darkGreen,
                    )
                    RoundedButton(
                        buttonText = "+",
                        textSize = LocalTextFormat.current.sizeMain,
                        onButtonPress = {
                            productViewModel.updateProduct(ProductModel())
                            navController.navigate(Screen.ProductAdd.route)
                        },
                        modifier = Modifier
                            .width(LocalDimensions.current.viewNormalPlus)
                            .height(LocalDimensions.current.viewNormalPlus),
                        borderColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        },
    ) { innerPadding ->
        ProductList(
            onProductSelected = { product: ProductModel ->
                productViewModel.updateProduct(product.copy())
                navController.navigate(Screen.ProductEdit.route)
            },
            onProductAdded = { product: ProductModel ->
                basketViewModel.addToBasket(product)
            },
            productList = uiState.productList,
            paddingValue = innerPadding
        )
    }
}


@Preview
@Composable
fun NewCatalogScreenPreview() {
    val productViewModel = remember { mutableStateOf(ProductViewModel()) }
    productViewModel.value.updateProduct(
        ProductModel(
            productName = "MyProduct1",
            vatRate = 10.0,
            price = 30.0
        )
    )
    val basketViewModel = remember { mutableStateOf(BasketViewModel()) }
    FrontendInternshipTheme {
        CatalogScreen(
            navController = rememberNavController(),
            viewModel = CatalogViewModel_Factory.newInstance(
                FakeLoadProductsUseCase()
            ), productViewModel = productViewModel.value, basketViewModel = basketViewModel.value
        )
    }
}

class FakeLoadProductsUseCase : ILoadProductsUseCase {
    override suspend fun invoke(): List<ProductModel> {
        return listOf(
            ProductModel(productName = "MyProduct1", vatRate = 10.0, price = 30.0),
            ProductModel(productName = "MyProduct2", vatRate = 15.0, price = 12.3450),
            ProductModel(productName = "MyProduct3", vatRate = 18.0, price = 30.40),
            ProductModel(
                productName = "MyBigProductNameItsBigItsVeryBig",
                vatRate = 0.0,
                price = 15.25
            ),
            ProductModel(productName = "mp2", vatRate = 20.0, price = 0.00),
            ProductModel(productName = "$*^($@!*@#", vatRate = 5.0, price = 5.49812940),
            ProductModel(productName = "m", vatRate = 20.0, price = 0.00),
            ProductModel(
                productName = "油売ってる自動販売機に重要な機材の欠片",
                vatRate = 5.0,
                price = 5.49812940
            ),
            ProductModel(
                productName = "Test Test Test Test Test Test Test Test Test Test Test ",
                vatRate = 5.0,
                price = 5.49812940
            ),
        )
    }
}
