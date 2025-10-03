package com.example.frontendinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import java.util.Vector

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
            MainAppScreen(plu0ProductList.size)
      }
    }
  }
}
@Preview
@Composable
fun AppPreview (){
    MainAppScreen(2)
}

@Composable
fun MainAppScreen(Size:Int) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomBar() })  {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = Size.toString()
            )
        }}
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


