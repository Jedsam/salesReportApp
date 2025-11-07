package com.example.frontendinternship.ui.screens.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.model.getTax
import com.example.frontendinternship.ui.common.viewmodel.ProductViewModel
import com.example.frontendinternship.ui.components.OrderProductList
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.TopBarWithReturn
import com.example.frontendinternship.ui.screens.product.ProductAddViewModel
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun BasketScreen(navController: NavController, viewModel: BasketViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val totalValue = uiState.productBasket.sumOf { it.getCost() }
    val taxTotal = uiState.productBasket.sumOf { it.getTax()}
    val subtotal = totalValue - taxTotal
    val discount = 0.0
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithReturn(
                navController = navController,
                currentScreenText = "Current Order",
                isConnected = true,
            )
        },
        bottomBar = {
            Column() {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalPadding.current.TinyPlus),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Total",
                        fontWeight = FontWeight.Bold,
                        fontSize = LocalTextFormat.current.sizeBig,
                    )
                    Text(
                        text = String.format(" %.2fTL", totalValue),
                        fontWeight = FontWeight.Bold,
                        fontSize = LocalTextFormat.current.sizeBig,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.Small)
                ) {
                    RoundedButton(
                        buttonText = "Proceed to Payment",
                        onButtonPress = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalPadding.current.Small)
                            .height(LocalDimensions.current.viewNormalPlus),
                        borderColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }) { innerPadding ->
        OrderProductList(
            productList = uiState.productBasket,
            paddingValue = innerPadding,
        ) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
            Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LocalPadding.current.Tiny),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Subtotal",
                            color = Color.Gray,
                        )
                        Text(
                            text = String.format(" %.2fTL", subtotal)
                        )
                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalPadding.current.Tiny),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tax",
                        color = Color.Gray,
                    )
                    Text(
                        text = String.format(" %.2fTL", taxTotal))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalPadding.current.Tiny),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Discount",
                        color = Color.Gray,
                    )
                    Text(
                        text = String.format(" %.2fTL", discount),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BasketScreenPreview() {
    val basketViewModel = remember {
        mutableStateOf(
            BasketViewModel(
            )
        )
    }
    basketViewModel.value.addToBasket(ProductModel(productName = "m", vatRate = 20.0, price = 0.00))
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "MyBigProductNameItsBigItsVeryBig",
            vatRate = 0.0,
            price = 15.25
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "DenemDenemDenemDenemeeeeDeneme",
            vatRate = 5.0,
            price = 5.49812940
        )
    )
    FrontendInternshipTheme {
        BasketScreen(navController = rememberNavController(), viewModel = basketViewModel.value)
    }
}

@Preview
@Composable
fun BasketScreenBigPreview() {
    val basketViewModel = remember {
        mutableStateOf(
            BasketViewModel(
            )
        )
    }
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "MyProduct1",
            vatRate = 10.0,
            price = 30.0
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "MyProduct2",
            vatRate = 15.0,
            price = 12.3450
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "MyProduct3",
            vatRate = 18.0,
            price = 30.40
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "MyBigProductNameItsBigItsVeryBig",
            vatRate = 0.0,
            price = 15.25
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "mp2",
            vatRate = 20.0,
            price = 0.00
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "$*^($@!*@#",
            vatRate = 5.0,
            price = 5.49812940
        )
    )
    basketViewModel.value.addToBasket(ProductModel(productName = "m", vatRate = 20.0, price = 0.00))
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "油売ってる自動販売機に重要な機材の欠片",
            vatRate = 5.0,
            price = 5.49812940
        )
    )
    basketViewModel.value.addToBasket(
        ProductModel(
            productName = "DenemDenemDenemDenemeeeeDeneme",
            vatRate = 5.0,
            price = 5.49812940
        )
    )
    FrontendInternshipTheme {
        BasketScreen(navController = rememberNavController(), viewModel = basketViewModel.value)
    }
}

