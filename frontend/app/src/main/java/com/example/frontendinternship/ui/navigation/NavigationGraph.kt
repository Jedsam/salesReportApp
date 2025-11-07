package com.example.frontendinternship.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontendinternship.ui.common.viewmodel.ProductViewModel
import com.example.frontendinternship.ui.screens.basket.BasketScreen
import com.example.frontendinternship.ui.screens.basket.BasketViewModel
import com.example.frontendinternship.ui.screens.catalog.CatalogScreen
import com.example.frontendinternship.ui.screens.login.LoginScreen
import com.example.frontendinternship.ui.screens.payment.PaymentScreen
import com.example.frontendinternship.ui.screens.payment.PaymentViewModel
import com.example.frontendinternship.ui.screens.product.ProductAddScreen
import com.example.frontendinternship.ui.screens.product.ProductEditScreen
import com.example.frontendinternship.ui.screens.register.RegisterScreen


@Composable
fun Navigation(
    navController: NavHostController,
    productViewModel: ProductViewModel = viewModel(),
    basketViewModel: BasketViewModel = viewModel(),
    paymentViewModel: PaymentViewModel = viewModel()
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
            route = Screen.Catalog.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            CatalogScreen(
                navController = navController,
                productViewModel = productViewModel,
                basketViewModel = basketViewModel
            )
        }
        composable(
            route = Screen.ProductEdit.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            ProductEditScreen(
                navController,
                viewModel = productViewModel
            )
        }
        composable(
            route = Screen.ProductAdd.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            ProductAddScreen(
                navController = navController,
                viewModel = productViewModel
            )
        }
        composable(
            route = Screen.Login.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            LoginScreen(
                navController,
            )
        }
        composable(
            route = Screen.Register.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            RegisterScreen(
                navController = navController,
            )
        }
        composable(
            route = Screen.Basket.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            BasketScreen(
                navController = navController,
                viewModel = basketViewModel,
                paymentViewModel = paymentViewModel
            )
        }
        composable(
            route = Screen.Payment.route,
            enterTransition = defaultEnterAnimation,
            exitTransition = defaultExitAnimation
        ) {
            PaymentScreen(
                navController = navController,
                viewModel = paymentViewModel
            )
        }

    }
}