package com.example.frontendinternship.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Money
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Shop
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.LocalDimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    navController: NavController,
    containerColor: Color = MaterialTheme.colorScheme.background,
    topBarRightSideContent: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    screenText: String,
    content: @Composable (PaddingValues) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerWidth = LocalDimensions.current.viewExtrasMax

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .width(drawerWidth)
                    .fillMaxHeight()
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .padding(start = 16.dp)
            ) {
                Column {
                    Text(
                        text = "MyApp",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(16.dp)
                    )
                    NavigationItem(
                        text = "Home",
                        onClick = {
                            navController.popBackStack(
                                Screen.Catalog.route,
                                inclusive = false
                            )
                        },
                        icon = Icons.Sharp.Home
                    )
                    NavigationItem(
                        text = "Transaction",
                        onClick = {
                            navController.navigate(Screen.Transaction.route)
                        },
                        icon = Icons.Sharp.Money
                    )
                    NavigationItem(
                        text = "Merchant",
                        onClick = {
                            navController.navigate(Screen.Merchant.route)
                        },
                        icon = Icons.Sharp.Person
                    )
                    NavigationItem(
                        text = "Shop",
                        onClick = {
                            navController.navigate(Screen.Shop.route)
                        },
                        icon = Icons.Sharp.Shop
                    )
                }
            }
        }
    ) {
        Scaffold(
            containerColor = containerColor,
            contentWindowInsets = WindowInsets.systemBars,
            topBar = {
                TopBarWithComponent(
                    onNavigationBarClick = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open()
                            else drawerState.close()
                        }
                    },
                    currentScreenText = screenText,
                ) { topBarRightSideContent() }
            },
            bottomBar = { bottomBar() },
            content = { innerPadding ->
                content(innerPadding)
            }
        )
    }
}

@Preview
@Composable
fun ScaffoldWithNavigationBarPreview() {
    MyScaffold(
        navController = rememberNavController(),
        topBarRightSideContent = {},
        bottomBar = {},
        screenText = "MyScreen",
        content = {})
}
