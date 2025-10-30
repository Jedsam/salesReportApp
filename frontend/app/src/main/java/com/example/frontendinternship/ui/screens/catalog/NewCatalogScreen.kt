package com.example.frontendinternship.ui.screens.catalog

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.components.AddProductButton
import com.example.frontendinternship.ui.components.ProductList
import com.example.frontendinternship.ui.components.TopBarWithSync
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme

@Composable
fun NewCatalogScreen(
    navController: NavController,
    viewModel: NewCatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            TopBarWithSync(
                onSyncButtonPressed = {},
                isConnected = true,
                currentScreenText = "Products"
            )
        },
        bottomBar = {},
        floatingActionButton = { AddProductButton(onButtonPressed = {}) },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        ProductList(
            onProductSelected = {},
            productList = uiState.productList,
            paddingValue = innerPadding
        )
    }
}


@Preview
@Composable
fun NewCatalogScreenPreview() {
    FrontendInternshipTheme {
        NewCatalogScreen(navController = rememberNavController())
    }
}