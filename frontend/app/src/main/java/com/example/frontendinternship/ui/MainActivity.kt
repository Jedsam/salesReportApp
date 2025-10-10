package com.example.frontendinternship.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.frontendinternship.ui.product.ProductScreen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrontendInternshipTheme {
                ProductScreen() // entry composable
            }
        }
    }
}