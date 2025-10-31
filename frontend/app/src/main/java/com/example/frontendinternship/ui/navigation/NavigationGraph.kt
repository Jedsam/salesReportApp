package com.example.frontendinternship.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontendinternship.ui.screens.catalog.NewCatalogScreen
import com.example.frontendinternship.ui.screens.catalog.ProductTransferViewModel
import com.example.frontendinternship.ui.screens.product.ProductScreen


@Composable
fun Navigation(
    navController: NavHostController,
    productTransferViewModel: ProductTransferViewModel = ProductTransferViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            NewCatalogScreen(navController, productTransferViewModel = productTransferViewModel)
        }
        composable(Screen.Product.route) {
            ProductScreen(
                navController,
                productTransferViewModel = productTransferViewModel
            )
        }
    }
}