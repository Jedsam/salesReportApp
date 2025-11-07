package com.example.frontendinternship.ui.navigation

sealed class Screen(val route: String) {
    data object ProductAdd : Screen("ProductAdd")
    data object ProductEdit : Screen("ProductEdit")
    data object Catalog : Screen("Catalog")
    data object Login : Screen("Login")
    data object Register : Screen("Register")
    data object Basket : Screen("Basket")
}