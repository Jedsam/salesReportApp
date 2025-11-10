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
import com.example.frontendinternship.ui.theme.LocalDimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
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
                    .background(Color.Blue)
                    .width(drawerWidth)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
            ) {
                Text(
                    text = "Drawer title",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
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
    MyScaffold(topBarRightSideContent = {}, bottomBar = {}, screenText = "MyScreen", content =  {})
}
