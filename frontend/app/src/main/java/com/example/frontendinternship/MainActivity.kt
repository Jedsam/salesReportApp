package com.example.frontendinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            MainAppScreen(plu0ProductList,plu1ProductList,plu10ProductList,   plu20ProductList)
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
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar() })  {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
        ) {
            Column(
                modifier = Modifier
                    .padding(LocalPadding.current.Normal),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {
                GetProductBoxes(plu0ProductList)
                GetProductBoxes(plu1ProductList)
                GetProductBoxes(plu10ProductList)
                GetProductBoxes(plu20ProductList)
            }
        }}
}

@Composable
fun GetProductBoxes(productList: List<Product>) {
    LazyRow (horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)) {
        items (productList) { product ->
            Box(
                modifier = Modifier
                    .size(width = LocalDimensions.current.viewLarge,height = LocalDimensions.current.viewNormal)
                    .clip(RoundedCornerShape(LocalDimensions.current.clipNormal))
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                ) {
                    Text(text = product.productName + "\n" + product.price , maxLines = 2,
                            lineHeight = LocalTextFormat.current.sizeNormal
                        ,
                        autoSize = TextAutoSize.StepBased(
                        minFontSize = LocalTextFormat.current.sizeTiny,
                        maxFontSize = LocalTextFormat.current.sizeNormal,
                        stepSize = 1.sp
                    ),textAlign = TextAlign.Center)
                }
            }
           }
    }
}
@Composable
fun TopBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

    Text(text = "WELCOME", fontSize = 30.sp)
    Text(text = "TIME:7:45", fontSize = 30.sp)
  }
}

@Composable
fun BottomBar() {
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
    Text(text = "SERVER: ins.ert.ip.here", fontSize = 30.sp)
  }
}


