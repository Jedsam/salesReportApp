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
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
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
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.ui.components.BottomBar
import com.example.frontendinternship.ui.components.GetActionButton
import com.example.frontendinternship.ui.components.GetProductButtons
import com.example.frontendinternship.ui.components.GetProductConfirmationWindow
import com.example.frontendinternship.ui.components.TopBar

@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()) {
    val basketListState: MutableState<List<ProductWithCount>> = remember {
        mutableStateOf(emptyList())
    }

    //LaunchedEffect(Unit) {
    // while (true) {
    //    delay(60_000)
    //   reportReceipt?.checkAndReportBasket(basketList)
    //}
    //}
    var basketList by basketListState
    val totalBasketPriceState: MutableFloatState = remember { mutableFloatStateOf(0f) }
    var totalBasketPrice by totalBasketPriceState
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
                val openDialog = remember { mutableStateOf(false)  }
                val currentProductState = remember { mutableStateOf<ProductWithCount?>(null)  }
                val currentCostState = remember { mutableFloatStateOf(0f)  }
                val productGroup by viewModel.products.collectAsState()
                GetProductConfirmationWindow(basketListState, totalBasketPriceState, currentProductState, currentCostState,openDialog)
                GetProductButtons(productGroup.plu0,  currentProductState, currentCostState, openDialog)
                GetProductButtons(productGroup.plu1, currentProductState,  currentCostState, openDialog)
                GetProductButtons(productGroup.plu10, currentProductState, currentCostState, openDialog)
                GetProductButtons(productGroup.plu20, currentProductState, currentCostState, openDialog)
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
                GetActionButton("CANCEL",
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CANCEL,
                )
                GetActionButton(
                    "CASH",
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CASH,
                )
                GetActionButton(
                    "CREDIT",
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CREDIT,
                )
                GetActionButton(
                    "COUPON",
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.COUPON,
                )
            }
            // Text( text = reportReceipt?.connectionStatusString ?: "")
            Text( text =  "")
        }
    }
}