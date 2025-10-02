package org.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview // Note the import change
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // setContent is the entry point for Composable UI in Android
    setContent {
      App() // Your original Composable function
    }
  }
}

@Composable
fun App() {
  MaterialTheme { Column(modifier = Modifier.padding(16.dp)) { Text("Hello, Android!") } }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
  App()
}
