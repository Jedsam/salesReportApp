package com.example.frontendinternship.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.ui.common.viewmodel.ProductTransferViewModel
import com.example.frontendinternship.ui.navigation.Navigation
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrontendInternshipTheme {
                val navController = rememberNavController()
                val productTransferViewModel = remember { ProductTransferViewModel() }
                Navigation(navController = navController, productTransferViewModel)
            }
        }
    }
}