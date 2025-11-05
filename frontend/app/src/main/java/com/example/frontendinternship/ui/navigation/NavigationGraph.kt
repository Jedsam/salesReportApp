package com.example.frontendinternship.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontendinternship.ui.screens.catalog.CatalogScreen
import com.example.frontendinternship.ui.common.viewmodel.ProductTransferViewModel
import com.example.frontendinternship.ui.screens.login.LoginScreen
import com.example.frontendinternship.ui.screens.product.ProductScreen
import com.example.frontendinternship.ui.screens.register.RegisterScreen


@Composable
fun Navigation(
    navController: NavHostController,
    productTransferViewModel: ProductTransferViewModel = ProductTransferViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            CatalogScreen(navController, productTransferViewModel = productTransferViewModel)
        }
        composable(Screen.Product.route) {
            ProductScreen(
                navController,
                productTransferViewModel = productTransferViewModel
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navController,
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                navController,
            )
        }
    }
}