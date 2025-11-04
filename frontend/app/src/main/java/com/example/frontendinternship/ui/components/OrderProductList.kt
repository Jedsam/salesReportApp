package com.example.frontendinternship.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalColors
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

// Add vat rates maybe?
@SuppressLint("DefaultLocale")
@Composable
fun OrderProductList(
    productList: List<ProductWithCount>,
    paddingValue: PaddingValues,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValue)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            modifier = Modifier
                .padding(paddingValue)
        ) {
            items(productList) { product ->
                Surface(
                    shape = RoundedCornerShape(15),
                    color = LocalColors.current.lightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = LocalPadding.current.Tiny,
                            end = LocalPadding.current.Small
                        )
                        .height(LocalDimensions.current.viewLarge),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalPadding.current.Tiny),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = product.product.productName,
                                fontSize = LocalTextFormat.current.sizeNormal,
                                color = Color.Black
                            )
                            Text(
                                text = String.format(" %.2f", product.product.price) + "TL",
                                fontSize = LocalTextFormat.current.sizeSmall,
                                color = Color.Gray
                            )
                        }
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Default.RemoveCircle,
                                contentDescription = "RemoveIcon",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(text = String.format("%03d", product.count))
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "AddIcon",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = String.format("%10.2f", product.product.price) + "TL",
                        )
                    }
                }
            }
            item {
                content()
            }
        }
    }
}


@Preview
@Composable
fun OrderProductListPreview() {
    FrontendInternshipTheme {
        Scaffold { innerPadding ->
            OrderProductList(
                productList = listOf(
                    ProductWithCount(
                        ProductModel(
                            productName = "MyProduct1",
                            vatRate = 10.0,
                            price = 30.0
                        ), 4
                    ),
                    ProductWithCount(
                        ProductModel(
                            productName = "MyBigProductNameItsBigItsVeryBig",
                            vatRate = 0.0,
                            price = 15.25
                        ), 10
                    ),
                    ProductWithCount(ProductModel(productName = "mp2", vatRate = 20.0, price = 0.00), 1),
                    ProductWithCount(
                        ProductModel(
                            productName = "$*^($@!*@#",
                            vatRate = 5.0,
                            price = 5.49812940
                        ), 594
                    ),
                ),
                paddingValue = innerPadding
            ) {}
        }
    }
}