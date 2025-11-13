package com.example.frontendinternship.ui.screens.transaction

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.usecase.authentication.ICheckUserLoggedInUseCase
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionsUseCase
import com.example.frontendinternship.domain.usecase.transaction.ISynchronizeTransactionsUseCase
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.TransactionList
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat
import com.example.frontendinternship.utils.APIOperationStateEnum

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
                        if (uiState.syncResult == APIOperationStateEnum.READY)
                            viewModel.synchronizeTransactions()
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
                val buttonText: String = if (uiState.isLoggedIn) {
                    when (uiState.syncResult) {
                        APIOperationStateEnum.READY -> "Sync"
                        APIOperationStateEnum.SUCCESS -> "Success!"
                        APIOperationStateEnum.FAILURE -> "Failure!"
                        APIOperationStateEnum.EXECUTING -> "Executing"
                    }
                } else {
                    "Login"
                }
                Text(
                    text = buttonText,
                    fontSize = LocalTextFormat.current.sizeBig,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        screenText = "Transactions",
        bottomBar = {

        },
    ) { innerPadding ->
        TransactionList(
            onTransactionSelected = {
                //transaction: TransactionModel ->
                //navController.navigate(Screen.Transaction.route)
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
                FakeLoadTransactionsUseCase(),
                FakeSynchronizeTransactionsUseCase(),
                FakeCheckUserLoggedInUseCase()
            )
        )
    }
}

class FakeCheckUserLoggedInUseCase : ICheckUserLoggedInUseCase {
    override suspend fun invoke(): Boolean {
        return false
    }
}

class FakeSynchronizeTransactionsUseCase : ISynchronizeTransactionsUseCase {
    override suspend fun invoke(): Boolean {
        return false
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
