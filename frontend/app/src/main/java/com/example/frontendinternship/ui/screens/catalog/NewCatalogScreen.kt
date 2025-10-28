package com.example.frontendinternship.ui.screens.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontendinternship.ui.components.ProductList
import com.example.frontendinternship.ui.components.TopBarWithSync

@Composable
fun NewCatalogScreen(
    navController: NavController,
    viewModel: NewCatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithSync(
                onSyncButtonPressed = {},
                isConnected = true,
                currentScreenText = "Products"
            )
        },
        bottomBar = {}) { innerPadding ->
        ProductList(
            onProductSelected = {},
            productList = uiState.productList,
            paddingValue = innerPadding
        )
    }
}

