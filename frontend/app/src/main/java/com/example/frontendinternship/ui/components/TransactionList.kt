package com.example.frontendinternship.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowForwardIos
import androidx.compose.material.icons.automirrored.sharp.Note
import androidx.compose.material.icons.sharp.CreditCard
import androidx.compose.material.icons.sharp.Money
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat
import com.example.frontendinternship.utils.PaymentTypeEnum

// Add vat rates maybe?
@SuppressLint("DefaultLocale")
@Composable
fun TransactionList(
    transactionList: List<TransactionModel>,
    onTransactionSelected: (TransactionModel) -> Unit,
    paddingValue: PaddingValues
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
        modifier = Modifier
            .padding(paddingValue),
    ) {
        items(transactionList) { transaction ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTransactionSelected(transaction) }
                    .padding(horizontal = LocalPadding.current.Tiny),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Transaction ID: ",
                        fontSize = LocalTextFormat.current.sizeNormal,
                        color = Color.Black
                    )
                    Text(
                        text = "#${transaction.transactionId}",
                        fontSize = LocalTextFormat.current.sizeTiny,
                        color = Color.Black
                    )
                    Text(
                        text = transaction.createdAt,
                        fontSize = LocalTextFormat.current.sizeSmall,
                        color = Color.Black
                    )
                    Row {
                        var icon: ImageVector
                        var paymentMethodText: String
                        when (transaction.paymentType) {
                            PaymentTypeEnum.CASH -> {
                                icon = Icons.Sharp.Money
                                paymentMethodText = "Cash"
                            }

                            PaymentTypeEnum.CREDIT -> {
                                icon = Icons.Sharp.CreditCard
                                paymentMethodText = "Credit"
                            }

                            PaymentTypeEnum.COUPON -> {
                                icon = Icons.AutoMirrored.Sharp.Note
                                paymentMethodText = "Coupon"
                            }
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = paymentMethodText,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = paymentMethodText,
                            fontSize = LocalTextFormat.current.sizeSmall,
                            color = Color.Gray
                        )
                    }
                }
                Column {
                    Text(
                        text = String.format(" %.2f", transaction.total) + "TL",
                        fontSize = LocalTextFormat.current.sizeNormal,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = String.format(" %.2f", transaction.subtotal) + "TL",
                        fontSize = LocalTextFormat.current.sizeSmall,
                        color = Color.Gray
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowForwardIos,
                    contentDescription = "ArrowRight",
                    tint = MaterialTheme.colorScheme.secondary
                )
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
fun TransactionListPreview() {
    FrontendInternshipTheme {
        Scaffold { innerPadding ->
            TransactionList(
                transactionList = listOf(
                    TransactionModel(
                        subtotal = 10.0,
                        total = 30.0,
                        paymentType = PaymentTypeEnum.COUPON,
                    ),
                    TransactionModel(
                        subtotal = 5.0,
                        total = 15.25,
                        paymentType = PaymentTypeEnum.CREDIT,
                    ),
                    TransactionModel(
                        subtotal = 3.1741515,
                        total = 20.0,
                        paymentType = PaymentTypeEnum.CASH,
                    ),
                    TransactionModel(
                        subtotal = 5.0,
                        total = 5.49812940,
                    ),
                ),
                onTransactionSelected = {},
                paddingValue = innerPadding
            )
        }
    }
}