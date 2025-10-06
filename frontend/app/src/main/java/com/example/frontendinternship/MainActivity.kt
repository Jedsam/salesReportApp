package com.example.frontendinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    plu20ProductList: List<Product>
) {
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
                GetProductButtons(plu0ProductList)
                GetProductButtons(plu1ProductList)
                GetProductButtons(plu10ProductList)
                GetProductButtons(plu20ProductList)
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
                    Text(text = "0xPRODUCT NAME", fontSize = LocalTextFormat.current.sizeLarge)
                    Text(text = "0", fontSize = LocalTextFormat.current.sizeLarge)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalPadding.current.Small),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "TOTAL", fontSize = LocalTextFormat.current.sizeLarge)
                    Text(text = "0", fontSize = LocalTextFormat.current.sizeLarge)
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
            ), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GetProductButtons(productList: List<Product>) {
    val openDialog = remember { mutableStateOf(false)  }
    var currentProduct: Product? by remember { mutableStateOf(null)  }


    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                currentProduct?.let { Text(text = "Confirm Product : " + it.productName ) }
            },
            text = {
                Text("")
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("This is the Confirm Button")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("This is the dismiss Button")
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
                    ), textAlign = TextAlign.Center
                )
            }
        }
           }
    }

@Composable
fun TopBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

    Text(text = "WELCOME", fontSize = LocalTextFormat.current.sizeMain)
    Text(text = "TIME:7:45", fontSize = LocalTextFormat.current.sizeMain)
  }
}

@Composable
fun BottomBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
    Text(text = "SERVER: ins.ert.ip.here", fontSize = LocalTextFormat.current.sizeMain)
  }
}


