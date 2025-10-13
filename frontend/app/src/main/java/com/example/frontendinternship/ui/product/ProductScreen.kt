package com.example.frontendinternship.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat
import androidx.compose.runtime.collectAsState
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.ui.components.GetActionButton
import com.example.frontendinternship.ui.product.components.BottomBar
import com.example.frontendinternship.ui.product.components.ConfirmationWindow
import com.example.frontendinternship.ui.product.components.ProductButtons
import com.example.frontendinternship.ui.product.components.TopBar

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()) {

    //LaunchedEffect(Unit) {
    // while (true) {
    //    delay(60_000)
    //   reportReceipt?.checkAndReportBasket(basketList)
    //}
    //}
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar("empty") }) { innerPadding ->
        // TODO bottomBar = { BottomBar(reportReceipt?.getCurrentConnectedIp() ?: "") })  {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
        ) {
            // Product boxes
            Column(
                modifier = Modifier
                    .padding(LocalPadding.current.Normal),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {


                ConfirmationWindow(
                    priceValue = uiState.confirmationPriceInput,
                    quantityValue = uiState.confirmationQuantityInput,
                    currentProduct = uiState.confirmationProduct,
                    currentCost = uiState.confirmationCost,
                    openWindow = uiState.isConfirmationWindowOpen,
                    onDismissRequest = viewModel::onConfirmationDismissed,
                    onQuantityFieldChange = viewModel::onQuantityFieldValueChanged,
                    onPriceFieldValueChange = viewModel::onPriceFieldValueChanged,
                    onConfirmClick = viewModel::onConfirmClick
                )
                ProductButtons(
                    productList = uiState.plu0,
                    onProductSelected = viewModel::onProductSelect
                )
                ProductButtons(
                    uiState.plu1,
                    onProductSelected = viewModel::onProductSelect
                )
                ProductButtons(
                    uiState.plu10,
                    onProductSelected = viewModel::onProductSelect
                )
                ProductButtons(
                    uiState.plu20,
                    onProductSelected = viewModel::onProductSelect
                )
            }
            // Order information
            Column(
                modifier = Modifier
                    .padding(LocalPadding.current.Normal),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (uiState.basketList.isNotEmpty()) {
                        val lastAddedProduct: ProductWithCount? =
                            uiState.basketList[uiState.basketList.size - 1]
                        Text(
                            text = lastAddedProduct?.count.toString() + "x" + lastAddedProduct?.product?.productName,
                            fontSize = LocalTextFormat.current.sizeLarge,
                            color = Color.Black
                        )
                        val lastAddedProductCost = lastAddedProduct?.getCost()
                        Text(
                            text = lastAddedProductCost.toString(),
                            fontSize = LocalTextFormat.current.sizeLarge,
                            color = Color.Black
                        )
                    } else {
                        Text(
                            text = "0xNO PRODUCT",
                            fontSize = LocalTextFormat.current.sizeLarge,
                            color = Color.Black
                        )
                        Text(
                            text = "0",
                            fontSize = LocalTextFormat.current.sizeLarge,
                            color = Color.Black
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TOTAL",
                        fontSize = LocalTextFormat.current.sizeLarge,
                        color = Color.Black
                    )
                    Text(
                        text = uiState.totalBasketPrice.toString(),
                        fontSize = LocalTextFormat.current.sizeLarge,
                        color = Color.Black
                    )
                }
            }
            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                GetActionButton(
                    text = "CANCEL",
                    basketList = uiState.basketList,
                    totalBasketPrice = uiState.totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CANCEL,
                    onButtonClick = viewModel::onActionButtonClick
                )
                GetActionButton(
                    text = "CASH",
                    basketList = uiState.basketList,
                    totalBasketPrice = uiState.totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CASH,
                    onButtonClick = viewModel::onActionButtonClick
                )
                GetActionButton(
                    text = "CREDIT",
                    basketList = uiState.basketList,
                    totalBasketPrice = uiState.totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CREDIT,
                    onButtonClick = viewModel::onActionButtonClick
                )
                GetActionButton(
                    text = "COUPON",
                    basketList = uiState.basketList,
                    totalBasketPrice = uiState.totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.COUPON,
                    onButtonClick = viewModel::onActionButtonClick
                )
            }
            // Text( text = reportReceipt?.connectionStatusString ?: "")
            Text(text = "")
        }
    }
}