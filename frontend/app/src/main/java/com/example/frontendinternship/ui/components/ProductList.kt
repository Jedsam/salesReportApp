package com.example.frontendinternship.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@SuppressLint("DefaultLocale")
@Composable
fun ProductList(
    productList: List<Product>,
    onProductSelected: (Product) -> Unit,
    paddingValue: PaddingValues
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)) {
        items(productList) { product ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = product.productName,
                        fontSize = LocalTextFormat.current.sizeNormal
                    )
                    Text(
                        text = String.format(" %.2f", product.price) + "TL",
                        fontSize = LocalTextFormat.current.sizeSmall,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = { onProductSelected(product) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "AddIcon",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
        }
    }
}


@Preview
@Composable
fun ProductListPreview() {
    FrontendInternshipTheme {
        ProductList(
            productList = listOf(
                Product(productName = "MyProduct1", vatRate = 10, price = 30.0f),
                Product(
                    productName = "MyBigProductNameItsBigItsVeryBig",
                    vatRate = 0,
                    price = 15.25f
                ),
                Product(productName = "mp2", vatRate = 20, price = 0.00f),
                Product(productName = "$*^($@!*@#", vatRate = 5, price = 5.49812940f),
            ),
            onProductSelected = {},
            paddingValue = PaddingValues(all = 0.dp)
        )
    }
}