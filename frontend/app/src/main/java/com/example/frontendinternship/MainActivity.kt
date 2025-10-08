package com.example.frontendinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.frontendinternship.data.local.entity.PAYMENT_METHOD
import com.example.frontendinternship.data.local.entity.Product
import com.example.frontendinternship.data.local.entity.ProductDao
import com.example.frontendinternship.data.local.entity.Receipt
import com.example.frontendinternship.data.local.entity.ReceiptDao
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.delay

@Database(
    entities = [Receipt::class, Product::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
    abstract fun productDao(): ProductDao
}

@HiltAndroidApp
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
      // Load database values
      val db = Room.databaseBuilder(
          applicationContext,
          AppDatabase::class.java, "product"
      )
          .createFromAsset("retail_2.db")
          .allowMainThreadQueries()
          .build()

      val productDao = db.productDao()
      val receiptDao = db.receiptDao()

      val reportReceipt = ReportReceiptViewModel(receiptDao)

      val plu0ProductList: List<Product> = productDao.loadAllByVat(vatValue = 0)
      val plu1ProductList: List<Product> = productDao.loadAllByVat(vatValue = 1)
      val plu10ProductList: List<Product>  = productDao.loadAllByVat(vatValue = 10)
      val plu20ProductList: List<Product>  = productDao.loadAllByVat(vatValue = 20)

    setContent {
      FrontendInternshipTheme {
            MainAppScreen(plu0ProductList, plu1ProductList, plu10ProductList, plu20ProductList, receiptDao, reportReceipt)
      }
    }
  }
}
@Preview
@Composable
fun AppPreview (){
    val plu0ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName = "PLU 001",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName = "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu1ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName = "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName = "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu10ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName = "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName = "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu20ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName = "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName = "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val receiptDao = null
    MainAppScreen(plu0ProductList, plu1ProductList, plu10ProductList, plu20ProductList, receiptDao, null)
}

@Composable
fun MainAppScreen(
    plu0ProductList: List<Product>,
    plu1ProductList: List<Product>,
    plu10ProductList: List<Product>,
    plu20ProductList: List<Product>,
    receiptDao: ReceiptDao?,
    reportReceipt: ReportReceiptViewModel?
) {


    val basketListState: MutableState<List<ProductWithCount>> = remember {
        mutableStateOf(emptyList())
    }
    var basketList by basketListState
    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000)
            reportReceipt?.checkAndReportBasket(basketList)
        }
    }



    val totalBasketPriceState: MutableFloatState = remember { mutableFloatStateOf(0f) }
    var totalBasketPrice by totalBasketPriceState
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar(reportReceipt?.getCurrentConnectedIp() ?: "") })  {innerPadding ->
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
                GetProductConfirmationWindow(basketListState, totalBasketPriceState, currentProductState, currentCostState,openDialog)
                GetProductButtons(plu0ProductList,  currentProductState, currentCostState, openDialog)
                GetProductButtons(plu1ProductList, currentProductState,  currentCostState, openDialog)
                GetProductButtons(plu10ProductList, currentProductState, currentCostState, openDialog)
                GetProductButtons(plu20ProductList, currentProductState, currentCostState, openDialog)
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
                    receiptDao,
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CANCEL,
                    reportReceipt)
                GetActionButton(
                    "CASH",
                    receiptDao,
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CASH,
                    reportReceipt
                )
                GetActionButton(
                    "CREDIT",
                    receiptDao,
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.CREDIT,
                    reportReceipt
                )
                GetActionButton(
                    "COUPON",
                    receiptDao,
                    basketListState,
                    totalBasketPriceState,
                    PAYMENT_METHOD.COUPON,
                    reportReceipt
                )
            }
            Text( text = reportReceipt?.connectionStatusString ?: "")
        }
    }
}



@Composable
fun GetActionButton(
    text: String,
    receiptDao: ReceiptDao?,
    basketListState: MutableState<List<ProductWithCount>>,
    totalBasketPriceState: MutableFloatState,
    paymentMethod: PAYMENT_METHOD,
    reportReceipt: ReportReceiptViewModel?
) {
    var basketList by basketListState
    var totalBasketPrice by totalBasketPriceState
    Button(
        onClick = {
           receiptDao?.insert(basketList, paymentMethod)
            basketList = emptyList()
            totalBasketPrice = 0f
            reportReceipt?.checkAndReportBasket(basketList)
                  },
        border = BorderStroke(
            width = 2.dp,            // Thickness of the border
            color = Color.Black        // Color of the border
        ),
        modifier = Modifier
            .size(
                width = LocalDimensions.current.viewLargePlus,
                height = LocalDimensions.current.viewBig
            ),
        // TODO Add color change when button is pressed
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Black,
            disabledContainerColor = Color.Blue,
            disabledContentColor = Color.Black,
        ),
         shape = TriangleCutCornerShape(16f),
        contentPadding = PaddingValues(horizontal = LocalPadding.current.VeryTiny, vertical = LocalPadding.current.Mini)
    ) {
        Text(modifier = Modifier.fillMaxWidth(),
            text = text, maxLines = 2,
            lineHeight = LocalTextFormat.current.sizeNormal,
            autoSize = TextAutoSize.StepBased(
                minFontSize = LocalTextFormat.current.sizeTiny,
                maxFontSize = LocalTextFormat.current.sizeNormal,
                stepSize = 1.sp
            ), textAlign = TextAlign.Center, color = Color.Black
        )
    }
}

@Composable
fun GetProductConfirmationWindow(
    basketListState: MutableState<List<ProductWithCount>>,
    totalBasketPriceState: MutableFloatState,
    currentProductState: MutableState<ProductWithCount?>, currentCostState: MutableFloatState, openDialog: MutableState<Boolean>
) {
    var basketList by basketListState
    var totalBasketPrice by totalBasketPriceState
    var currentProduct by currentProductState
    var currentCost by currentCostState

    if (openDialog.value) {
        var quantityValue by rememberSaveable { mutableStateOf("1") }
        var priceValue by rememberSaveable { mutableStateOf(currentProduct?.product?.price ?:"1") }
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                currentProduct?.let { Text(text = "Confirm Product\n" + it.product.productName, fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black) }
            },
            text = {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Price", color = Color.Black)
                        BasicTextField(
                            value = priceValue,
                            onValueChange = { newText ->
                                val tempText = newText.filter { it.isDigit() || it == '.' }
                                val textFloat = tempText.toFloatOrNull() ?: 0f
                                if (textFloat <= 999.99 && textFloat >= 0.01)
                                    priceValue = tempText
                                currentProduct?.product?.price = textFloat.toString()
                                currentCost = currentProduct?.getCost() ?: 0f
                            },
                            modifier = Modifier
                                .size(
                                    width = LocalDimensions.current.viewLarge,
                                    height = LocalDimensions.current.viewSmall
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.Black
                                )
                                .padding(LocalPadding.current.Tiny),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Quantity", color = Color.Black)
                        BasicTextField(
                            value = quantityValue,
                            onValueChange = { newText ->
                                val tempText = newText.filter { it.isDigit() }
                                val textInt = tempText.toIntOrNull() ?: 0
                                if (textInt <= 99 && textInt >= 1)
                                    quantityValue = tempText
                                currentProduct?.count = textInt
                                currentCost = currentProduct?.getCost() ?: 0f
                            },
                            modifier = Modifier
                                .size(
                                    width = LocalDimensions.current.viewNormal,
                                    height = LocalDimensions.current.viewSmall
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.Black
                                )
                                .padding(LocalPadding.current.Tiny),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Total Price", color = Color.Black)
                        Box {
                            Text(text = currentCost.toString(), textDecoration = TextDecoration.Underline, color = Color.Black)
                        }
                    }
                }
            }, containerColor = MaterialTheme.colorScheme.background,
            confirmButton = {
                Button(
                    onClick = {
                        // Save the item on the shop list
                        currentProduct?.let {
                            basketList = basketList + it
                            totalBasketPrice += currentCost
                        }
                        openDialog.value = false
                    }, colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Blue,
                        disabledContentColor = Color.Black,
                    ),) {
                    Text("Confirm", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }, colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Blue,
                        disabledContentColor = Color.Black,),
                ) {
                    Text("Cancel", color = Color.Black)
                }
            }
        )
    }
}
@Composable
fun GetProductButtons(
    productList: List<Product>,
    currentProductState: MutableState<ProductWithCount?>, currentCostState: MutableFloatState, openDialog: MutableState<Boolean>
) {
    var currentProduct: ProductWithCount? by currentProductState
    var currentCost: Float by currentCostState


    LazyRow (horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)) {
        items (productList) { product ->
            Button(
                onClick = {
                    currentProduct = ProductWithCount(product.copy(), 1)
                    currentCost = currentProduct?.getCost() ?: 0f
                    openDialog.value = true
                          },
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .size(
                        width = LocalDimensions.current.viewLarge,
                        height = LocalDimensions.current.viewBig
                    ),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Blue,
                    disabledContentColor = Color.Black,
                ),
                shape = RoundedCornerShape(LocalDimensions.current.clipTiny),
                contentPadding = PaddingValues(horizontal = LocalPadding.current.VeryTiny, vertical = LocalPadding.current.Mini)
            ) {
                Text(modifier = Modifier.fillMaxWidth(),
                    text = product.productName + "\n" + product.price, maxLines = 2,
                    lineHeight = LocalTextFormat.current.sizeNormal,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = LocalTextFormat.current.sizeTiny,
                        maxFontSize = LocalTextFormat.current.sizeNormal,
                        stepSize = 1.sp
                    ), textAlign = TextAlign.Center, color = Color.Black
                )
            }
        }
           }
    }

@Composable
fun TopBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

    Text(text = "WELCOME", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
    Text(text = "TIME:7:45", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
  }
}

@Composable
fun BottomBar(string: String) {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
      Text(text = "SERVER: ",fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
    Text(text = string, maxLines = 2, autoSize = TextAutoSize.StepBased(
        minFontSize = LocalTextFormat.current.sizeLarge,
        maxFontSize = LocalTextFormat.current.sizeMain,
        stepSize = 1.sp
    ), color = Color.Black)
  }
}


