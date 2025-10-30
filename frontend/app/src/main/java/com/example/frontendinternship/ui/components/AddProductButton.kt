package com.example.frontendinternship.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun AddProductButton(
    onButtonPressed: () -> Unit
) {
    FloatingActionButton(
        onClick = { onButtonPressed() },
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(10)
    ) {
        Text(
            text = "+",
            fontSize = LocalTextFormat.current.sizeMain,
        )
    }
}

@Preview
@Composable
fun AddProductButtonPreview() {
    FrontendInternshipTheme {
        AddProductButton(
            onButtonPressed = {}
        )
    }
}

