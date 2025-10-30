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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.OrderProductList
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.TopBarWithReturn
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.screens.product.ProductViewModel
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun BasketScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithReturn(
                navController = navController,
                screenToGoBackTo = Screen.Catalog,
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
                        text = "100.00TL",
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
            productList = emptyList(),
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
                    Text(text = "20.00TL")
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
                    Text(text = "40.00TL")
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
                        text = "-10.00TL",
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
    FrontendInternshipTheme {
        BasketScreen(navController = rememberNavController())
    }
}

