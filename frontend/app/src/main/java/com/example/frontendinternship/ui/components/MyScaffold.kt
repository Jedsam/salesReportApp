package com.example.frontendinternship.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val drawerWidth = LocalDimensions.current.viewExtrasNormal

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .width(drawerWidth)
                    .fillMaxHeight()
            ) {
                Column {
                    Text(
                        text = "Menu title",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "Transaction",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(Screen.Transaction.route)
                                }
                            )
                    )
                    Text(
                        text = "Shop",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(Screen.Shop.route)
                                }
                            )
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
