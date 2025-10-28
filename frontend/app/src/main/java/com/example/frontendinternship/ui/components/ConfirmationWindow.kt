package com.example.frontendinternship.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun ConfirmationWindow(
    priceValue: String,
    quantityValue: String,
    currentProduct: ProductWithCount?,
    currentCost: Float,
    openWindow: Boolean,
    onDismissRequest: () -> Unit,
    onQuantityFieldChange: (String) -> Unit,
    onPriceFieldValueChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
) {
    if (openWindow) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = {
                currentProduct?.let {
                    Text(
                        text = "Confirm Product\n" + it.product.productName,
                        fontSize = LocalTextFormat.current.sizeLarge,
                        color = Color.Black
                    )
                }
            },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Price", color = Color.Black)
                        BasicTextField(
                            value = priceValue,
                            onValueChange = { newText: String -> onPriceFieldValueChange(newText) },
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
                            onValueChange = { newText -> onQuantityFieldChange(newText) },
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
                            Text(
                                text = currentCost.toString(),
                                textDecoration = TextDecoration.Underline,
                                color = Color.Black
                            )
                        }
                    }
                }
            }, containerColor = MaterialTheme.colorScheme.background,
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmClick()
                    },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Blue,
                        disabledContentColor = Color.Black,
                    ),
                ) {
                    Text("Confirm", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismissRequest()
                    },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Blue,
                        disabledContentColor = Color.Black,
                    ),
                ) {
                    Text("Cancel", color = Color.Black)
                }
            }
        )
    }
}
