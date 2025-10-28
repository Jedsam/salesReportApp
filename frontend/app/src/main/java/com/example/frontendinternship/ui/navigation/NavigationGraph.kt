package com.example.frontendinternship.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontendinternship.ui.screens.catalog.NewCatalogScreen
import com.example.frontendinternship.ui.screens.product.ProductScreen


@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            NewCatalogScreen(navController)
        }
        composable(Screen.Product.route) {
            ProductScreen(navController)
        }
    }
}