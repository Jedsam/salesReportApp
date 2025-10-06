package com.example.frontendinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
      // Load database values
      val db = Room.databaseBuilder(
          applicationContext,
          AppDatabase::class.java, "product"
      )
          .createFromAsset("retail.db")
          .allowMainThreadQueries()
          .build()

      val productDao = db.productDao()

      val plu0ProductList: List<Product> = productDao.loadAllByVat(vatValue = 0)
      val plu1ProductList: List<Product> = productDao.loadAllByVat(vatValue = 1)
      val plu10ProductList: List<Product>  = productDao.loadAllByVat(vatValue = 10)
      val plu20ProductList: List<Product>  = productDao.loadAllByVat(vatValue = 20)
    setContent {
      FrontendInternshipTheme {
            MainAppScreen(plu0ProductList, plu1ProductList, plu10ProductList, plu20ProductList)
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
            productName =  "PLU 001",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName =  "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu1ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName =  "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName =  "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu10ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName =  "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName =  "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    val plu20ProductList: List<Product> = listOf(
        Product(
            id = 1,
            productName =  "product1",
            vatRate = 0,
            price = "5.0"
        ),
        Product(
            id = 2,
            productName =  "product2",
            vatRate = 0,
            price = "5.0"
        ),
    )
    MainAppScreen(plu0ProductList, plu1ProductList, plu10ProductList, plu20ProductList)
}

@Composable
fun MainAppScreen(
    plu0ProductList: List<Product>,
    plu1ProductList: List<Product>,
    plu10ProductList: List<Product>,
    plu20ProductList: List<Product>,
) {
    val basketListState: MutableState<List<ProductWithCount>> = remember {
        mutableStateOf(emptyList<ProductWithCount>())
    }
    var basketList by basketListState

    val totalBasketPriceState: MutableFloatState = remember { mutableFloatStateOf(0f) }
    val totalBasketPrice by totalBasketPriceState
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar() })  {innerPadding ->
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
                GetProductButtons(plu0ProductList, basketListState, totalBasketPriceState)
                GetProductButtons(plu1ProductList, basketListState, totalBasketPriceState)
                GetProductButtons(plu10ProductList, basketListState, totalBasketPriceState)
                GetProductButtons(plu20ProductList, basketListState, totalBasketPriceState)
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
                GetActionButton("CANCEL", {})
                GetActionButton("CASH", {})
                GetActionButton("CREDIT", {})
                GetActionButton("COUPON", {})
            }
        }
    }
}


@Composable
fun GetActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
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
fun GetProductButtons(
    productList: List<Product>,
    basketListState: MutableState<List<ProductWithCount>>, totalBasketPriceState: MutableFloatState
) {
    val openDialog = remember { mutableStateOf(false)  }
    var currentProduct: Product? by remember { mutableStateOf(null)  }
    var basketList by basketListState
    var totalBasketPrice by totalBasketPriceState


    if (openDialog.value) {
        var quantityValue by remember { mutableStateOf("1") }
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                currentProduct?.let { Text(text = "Confirm Product\n" + it.productName, fontSize = LocalTextFormat.current.sizeLarge, color = Color.Black) }
            },
            text = {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Text("Quantity", color = Color.Black)
                    BasicTextField(value = quantityValue,
                        onValueChange = { newText ->
                            quantityValue = newText.filter { it.isDigit() }
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Optional: Make it wide
                            .padding(10.dp)  // Optional: Add space around the border
                            // 🛠️ FIX: Apply the border modifier to draw the square
                            .border(
                                width = 2.dp,
                                color = Color.Black
                            )
                            // Optional: Add padding *inside* the border to separate text from the lines
                            .padding(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Save the item on the shop list
                        currentProduct?.let { newProduct ->
                            val newItem = ProductWithCount(newProduct, quantityValue.toInt())
                            basketList = basketList + newItem
                            totalBasketPrice += newItem.getCost()
                        }
                            openDialog.value = false
                    }) {
                    Text("This is the Confirm Button", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("This is the dismiss Button", color = Color.Black)
                }
            }
        )
    }
    LazyRow (horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)) {
        items (productList) { product ->
            Button(
                onClick = {
                    currentProduct = product
                    openDialog.value = true
                          },
                border = BorderStroke(
                    width = 2.dp,            // Thickness of the border
                    color = Color.Black        // Color of the border
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
fun BottomBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
    Text(text = "SERVER: ins.ert.ip.here", fontSize = LocalTextFormat.current.sizeMain, color = Color.Black)
  }
}


