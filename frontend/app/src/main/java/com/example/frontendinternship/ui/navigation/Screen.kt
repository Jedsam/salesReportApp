package com.example.frontendinternship.ui.navigation

sealed class Screen(val route: String) {
    data object Product : Screen("product")
    data object Catalog : Screen("catalog")
}