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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat
import androidx.compose.runtime.collectAsState
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.ui.components.BottomBar
import com.example.frontendinternship.ui.components.GetActionButton
import com.example.frontendinternship.ui.components.ProductButtons
import com.example.frontendinternship.ui.components.TopBar
import com.example.frontendinternship.ui.product.components.ConfirmationWindow

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()) {

    //LaunchedEffect(Unit) {
    // while (true) {
    //    delay(60_000)
    //   reportReceipt?.checkAndReportBasket(basketList)
    //}
    //}
    var basketList: List<ProductWithCount> by remember { mutableStateOf(emptyList()) }
    var totalBasketPrice: Float by remember { mutableFloatStateOf(0f) }
    val openDialogState = remember { mutableStateOf(false) }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar("empty") })  {innerPadding ->
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
                var openWindow by remember { mutableStateOf(false)  }
                var currentProduct by remember { mutableStateOf<ProductWithCount?>(null)  }
                var currentCost by remember { mutableFloatStateOf(0f)  }
                val productGroup by viewModel.products.collectAsState()


                var quantityValue by remember { mutableStateOf("1") }
                var priceValue by remember { mutableStateOf(currentProduct?.product?.price ?:"1") }
                val onDismissal = { openWindow = false}
                val onPriceFieldValueChanged = { newText: String->
                    val tempText = newText.filter { it.isDigit() || it == '.' }
                    val textFloat = tempText.toFloatOrNull() ?: 0f
                    if (textFloat <= 999.99 && textFloat >= 0.01)
                        priceValue = tempText
                    currentProduct?.product?.price = textFloat.toString()
                    currentCost = currentProduct?.getCost() ?: 0f
                }
                val onQuantityFieldValueChanged = { newText: String->
                    val tempText = newText.filter { it.isDigit() }
                    val textInt = tempText.toIntOrNull() ?: 0
                    if (textInt <= 99 && textInt >= 1)
                        quantityValue = tempText
                    currentProduct?.count = textInt
                    currentCost = currentProduct?.getCost() ?: 0f
                }
                val onConfirmClick = {
                    // Save the item on the shop list
                    currentProduct?.let {
                        basketList = basketList + it
                        totalBasketPrice += currentCost
                    }
                    openWindow = false
                }
                ConfirmationWindow(priceValue = priceValue,
                    quantityValue = quantityValue,
                    currentProduct = currentProduct,
                    currentCost = currentCost,
                    openWindow = openWindow,
                    onDismissRequest = onDismissal,
                    onQuantityFieldChange =  onQuantityFieldValueChanged,
                    onPriceFieldValueChange = onPriceFieldValueChanged,
                    onConfirmClick = onConfirmClick )
                val onProductSelect = { selectedProduct: Product ->
                    currentProduct = ProductWithCount(selectedProduct.copy(), 1)
                    currentCost = currentProduct?.getCost() ?: 0f
                    openDialogState.value = true
                openWindow = true}
                ProductButtons(productList = productGroup.plu0,
                    onProductSelected = onProductSelect)
                ProductButtons(productGroup.plu1,
                    onProductSelected = onProductSelect)
                ProductButtons(productGroup.plu10,
                    onProductSelected = onProductSelect)
                ProductButtons(productGroup.plu20,
                    onProductSelected = onProductSelect)
            }
            // Order information
            Column(
                modifier = Modifier
                    .padding(LocalPadding.current.Normal),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalPadding.current.Small),horizontalArrangement = Arrangement.SpaceBetween) {
                    if (basketList.isNotEmpty()) {
                        val lastAddedProduct: ProductWithCount? = basketList[basketList.size - 1]
                        Text(text = lastAddedProduct?.count.toString() + "x" + lastAddedProduct?.product?.productName,fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                        val lastAddedProductCost = lastAddedProduct?.getCost()
                        Text(text = lastAddedProductCost.toString(), fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                    } else {
                        Text(text = "0xNO PRODUCT",fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                        Text(text = "0", fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalPadding.current.Small),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "TOTAL", fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                    Text(text = totalBasketPrice.toString(), fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black)
                }
            }
            // Action buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                val onActionButtonClick = { paymentMethod: PAYMENT_METHOD ->
                    //receiptDao?.insert(basketList, paymentMethod)
                    basketList = emptyList()
                    totalBasketPrice = 0f
                    //reportReceipt?.checkAndReportBasket(basketList)
                }
                GetActionButton(
                    text = "CANCEL",
                    basketList = basketList,
                    totalBasketPrice = totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CANCEL,
                    onButtonClick = onActionButtonClick
                )
                GetActionButton(
                    text = "CASH",
                    basketList = basketList,
                    totalBasketPrice = totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CASH,
                    onButtonClick = onActionButtonClick
                )
                GetActionButton(
                    text = "CREDIT",
                    basketList = basketList,
                    totalBasketPrice = totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.CREDIT,
                    onButtonClick = onActionButtonClick
                )
                GetActionButton(
                    text = "COUPON",
                    basketList = basketList,
                    totalBasketPrice = totalBasketPrice,
                    paymentMethod = PAYMENT_METHOD.COUPON,
                    onButtonClick = onActionButtonClick
                )
            }
            // Text( text = reportReceipt?.connectionStatusString ?: "")
            Text( text =  "")
        }
    }
}