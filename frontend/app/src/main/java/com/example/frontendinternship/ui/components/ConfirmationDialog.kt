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
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

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
