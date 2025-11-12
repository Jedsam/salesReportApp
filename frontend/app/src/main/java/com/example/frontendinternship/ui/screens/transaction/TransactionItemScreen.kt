package com.example.frontendinternship.ui.screens.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.WifiOnorOff
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalColors
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun TransactionItemScreen(
    navController: NavController,
    viewModel: TransactionItemViewModel = hiltViewModel(),
    transactionId: String
) {
    LaunchedEffect(transactionId) { viewModel.loadAllTransactionItems(transactionId) }
    val uiState by viewModel.uiState.collectAsState()

    MyScaffold(
        navController = navController,
        containerColor = LocalColors.current.minorGray,
        topBarRightSideContent = {
            WifiOnorOff(isOn = true)
        },
        screenText = "TransactionItem",
        bottomBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                Spacer(
                    modifier = Modifier.height(LocalDimensions.current.viewMini)
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
                        text = " ${"%.2f".format(uiState.transactionWithItemModel.transaction.total)}TL",
                        fontWeight = FontWeight.Bold,
                        fontSize = LocalTextFormat.current.sizeBig,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.Small)
                ) {
                }
            }
        }) { innerPadding ->
    }
}

@Preview
@Composable
fun TransactionItemScreenPreview() {
    FrontendInternshipTheme {
        TransactionItemScreen(
            navController = rememberNavController(),
            transactionId = ""
        )
    }
}


