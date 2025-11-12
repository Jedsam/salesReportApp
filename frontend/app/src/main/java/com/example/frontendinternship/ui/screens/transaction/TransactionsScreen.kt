package com.example.frontendinternship.ui.screens.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionsUseCase
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.TransactionList
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalColors
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TransactionsScreen(
    navController: NavController,
    viewModel: TransactionsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MyScaffold(
        navController = navController,
        topBarRightSideContent = {
            Button(
                onClick = {
                    if (uiState.isLoggedIn) {
                        // onSyncButtonPressed()
                    } else {
                        navController.navigate(Screen.Login.route)
                    }
                },
                modifier = Modifier
                    .size(
                        width = LocalDimensions.current.viewBig,
                        height = LocalDimensions.current.viewBig
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.background,
                ),
                contentPadding = PaddingValues(
                    horizontal = LocalPadding.current.VeryTiny,
                    vertical = LocalPadding.current.Mini
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = LocalTextFormat.current.sizeBig,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        screenText = "Transactions",
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.Small)
                ) {
                    RoundedButton(
                        buttonText = "Go to Basket ( )",
                        onButtonPress = {
                            navController.navigate(Screen.Basket.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(horizontal = LocalPadding.current.Small)
                            .height(LocalDimensions.current.viewNormalPlus),
                        borderColor = LocalColors.current.darkGreen,
                        containerColor = LocalColors.current.darkGreen,
                    )
                }
            }
        },
    ) { innerPadding ->
        TransactionList(
            onTransactionSelected = { transaction: TransactionModel ->
                navController.navigate(Screen.Transaction.route)
            },
            transactionList = uiState.transactionList,
            paddingValue = innerPadding
        )
    }
}


@Preview
@Composable
fun NewCatalogScreenPreview() {
    FrontendInternshipTheme {
        TransactionsScreen(
            navController = rememberNavController(),
            viewModel = TransactionsViewModel_Factory.newInstance(
                FakeLoadTransactionsUseCase()
            )
        )
    }
}

class FakeLoadTransactionsUseCase : ILoadTransactionsUseCase {
    override suspend fun invoke(): List<TransactionModel> {
        return listOf(
            TransactionModel(
                subtotal = 10.0,
                total = 30.0
            ),
            TransactionModel(
                subtotal = 5.0,
                total = 15.25
            ),
            TransactionModel(
                subtotal = 3.1741515,
                total = 20.0
            ),
            TransactionModel(
                subtotal = 5.0,
                total = 5.49812940
            ),
        )
    }
}
