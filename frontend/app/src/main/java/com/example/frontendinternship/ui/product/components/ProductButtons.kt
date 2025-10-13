package com.example.frontendinternship.ui.product.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun ProductButtons(
    productList: List<Product>,
    onProductSelected: (Product) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)) {
        items(productList) { product ->
            Button(
                onClick = { onProductSelected(product) },
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
                contentPadding = PaddingValues(
                    horizontal = LocalPadding.current.VeryTiny,
                    vertical = LocalPadding.current.Mini
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
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
