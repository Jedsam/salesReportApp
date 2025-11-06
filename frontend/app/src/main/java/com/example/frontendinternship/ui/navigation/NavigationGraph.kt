package com.example.frontendinternship.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontendinternship.ui.common.viewmodel.ProductTransferViewModel
import com.example.frontendinternship.ui.screens.catalog.CatalogScreen
import com.example.frontendinternship.ui.screens.login.LoginScreen
import com.example.frontendinternship.ui.screens.product.ProductAddScreen
import com.example.frontendinternship.ui.screens.product.ProductEditScreen
import com.example.frontendinternship.ui.screens.register.RegisterScreen


@Composable
fun Navigation(
    navController: NavHostController,
    productTransferViewModel: ProductTransferViewModel
) {
    val defaultEnterAnimation: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
        slideInHorizontally(
            animationSpec = tween(0, easing = EaseInOut),
            initialOffsetX = { fullWidth -> fullWidth }  // from right
        )
    }
    val defaultExitAnimation: AnimatedContentTransitionScope<*>.() -> ExitTransition =
        {
            slideOutHorizontally(
                animationSpec = tween(0, easing = EaseInOut),
                targetOffsetX = { fullWidth -> fullWidth }  // to right
            )
        }
    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(
            Screen.Catalog.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            CatalogScreen(navController, productTransferViewModel = productTransferViewModel)
        }
        composable(
            Screen.ProductEdit.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            ProductEditScreen(
                navController,
                productTransferViewModel = productTransferViewModel
            )
        }
        composable(
            Screen.ProductAdd.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            ProductAddScreen(
                navController,
                productTransferViewModel = productTransferViewModel
            )
        }
        composable(
            Screen.Login.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            LoginScreen(
                navController,
            )
        }
        composable(
            Screen.Register.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            RegisterScreen(
                navController,
            )
        }
    }
}