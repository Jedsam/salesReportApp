package com.example.frontendinternship.ui.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.PaymentSwapButton
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.RoundedTextField
import com.example.frontendinternship.ui.components.TopBarWithReturn
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalColors
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.utils.PaymentTypeEnum

@Composable
fun PaymentScreen(
    navController: NavController,
    viewModel: PaymentViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithReturn(
                navController = navController,
                currentScreenText = "Payment",
                isConnected = true,
            )
        },
        bottomBar = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.Small)
                ) {
                    RoundedButton(
                        buttonText = "Charge ${"%.2f".format(uiState.payment.total)}TL",
                        onButtonPress = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalPadding.current.Small)
                            .height(LocalDimensions.current.viewNormalPlus),
                        borderColor = LocalColors.current.blueCerulean,
                        containerColor = LocalColors.current.blueCerulean,
                    )
                }
            }
        }) { innerPadding ->
        val isCash = uiState.payment.paymentType == PaymentTypeEnum.CASH
        val isCredit = uiState.payment.paymentType == PaymentTypeEnum.CREDIT
        val isCoupon = uiState.payment.paymentType == PaymentTypeEnum.COUPON
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .height(LocalDimensions.current.viewTiny)
            )
            Text(
                text = "Total Due",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "$${"%.2f".format(uiState.payment.total)}",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(LocalDimensions.current.viewNormalPlus)
                    .clip(RoundedCornerShape(LocalDimensions.current.viewMini))
                    .background(Color.LightGray),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(LocalDimensions.current.viewVeryMini))
                PaymentSwapButton(
                    onClick = {},
                    text = "Cash",
                    fillMaxWidthFraction = 0.3f,
                    isActive = isCash
                )
                PaymentSwapButton(
                    onClick = {},
                    text = "Credit",
                    fillMaxWidthFraction = 0.5f,
                    isActive = isCredit,
                )
                PaymentSwapButton(
                    onClick = {},
                    text = "Coupon",
                    isActive = isCoupon,
                    fillMaxWidthFraction = 0.96f,
                )
                Spacer(modifier = Modifier.width(LocalDimensions.current.viewVeryMini))
            }
            if (isCredit) {
                RoundedTextField(
                    textFieldInformation = "Card Number",
                    textValue = "",
                    onFieldValueChange = {
                    },
                    keyboardType = KeyboardType.Text,
                    textFieldModifier = Modifier.fillMaxWidth(0.9f),
                    textColor = Color.Black,
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = LocalPadding.current.Normal)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)
                ) {
                    RoundedTextField(
                        textFieldInformation = "Expiration Date",
                        textValue = "",
                        onFieldValueChange = {
                        },
                        keyboardType = KeyboardType.Decimal,
                        textFieldModifier = Modifier.fillMaxWidth(0.45f),
                        textColor = Color.Black,
                    )
                    RoundedTextField(
                        textFieldInformation = "CVV",
                        textValue = "",
                        onFieldValueChange = {
                        },
                        keyboardType = KeyboardType.Number,
                        textFieldModifier = Modifier.fillMaxWidth(),
                        textColor = Color.Black,
                    )
                }
            } else if (isCash) {
                RoundedTextField(
                    textFieldInformation = "Cash amount",
                    textValue = "",
                    onFieldValueChange = {
                    },
                    keyboardType = KeyboardType.Text,
                    textFieldModifier = Modifier.fillMaxWidth(0.9f),
                    textColor = Color.Black,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                )
                val taxTotal = uiState.payment.total - uiState.payment.subtotal
                Column{
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
                        Text(
                            text = " ${"%.2f".format(uiState.payment.subtotal)}TL"
                        )
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
                        Text(
                            text = " ${"%.2f".format(taxTotal)}TL"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LocalPadding.current.Tiny),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Change",
                            color = Color.Gray,
                        )
                        Text(
                            text = " ${"%.2f".format(0.0)}TL",
                            color = Color.Red
                        )
                    }
                }
            } else if (isCoupon) {
                RoundedTextField(
                    textFieldInformation = "Coupon Code",
                    textValue = "",
                    onFieldValueChange = {
                    },
                    keyboardType = KeyboardType.Text,
                    textFieldModifier = Modifier.fillMaxWidth(0.9f),
                    textColor = Color.Black,
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = LocalPadding.current.Normal)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal)
                ) {
                    RoundedTextField(
                        textFieldInformation = "Coupon Value",
                        textValue = "",
                        onFieldValueChange = {
                        },
                        keyboardType = KeyboardType.Decimal,
                        textFieldModifier = Modifier.fillMaxWidth(0.45f),
                        textColor = Color.Black,
                    )
                    RoundedTextField(
                        textFieldInformation = "Expiry Date",
                        textValue = "",
                        onFieldValueChange = {
                        },
                        keyboardType = KeyboardType.Number,
                        textFieldModifier = Modifier.fillMaxWidth(),
                        textColor = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentScreenCashPreview() {
    val paymentViewModel = remember { mutableStateOf(PaymentViewModel()) }
    paymentViewModel.value.changeToCashPayment()
    FrontendInternshipTheme {
        PaymentScreen(
            navController = rememberNavController(),
            viewModel = paymentViewModel.value
        )
    }
}

@Preview
@Composable
fun PaymentScreenCreditPreview() {
    val paymentViewModel = remember { mutableStateOf(PaymentViewModel()) }
    paymentViewModel.value.changeToCreditPayment()
    FrontendInternshipTheme {
        PaymentScreen(
            navController = rememberNavController(),
            viewModel = paymentViewModel.value
        )
    }
}

@Preview
@Composable
fun PaymentScreenCouponPreview() {
    val paymentViewModel = remember { mutableStateOf(PaymentViewModel()) }
    paymentViewModel.value.changeToCouponPayment()
    FrontendInternshipTheme {
        PaymentScreen(
            navController = rememberNavController(),
            viewModel = paymentViewModel.value
        )
    }
}
