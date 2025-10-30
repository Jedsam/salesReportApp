package com.example.frontendinternship.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun RoundedButton(
    buttonText: String,
    onButtonPress: () -> Unit,
) {
    Button(onClick = onButtonPress, shape = RoundedCornerShape(50)) {
        Text(
            text = buttonText,
            fontSize = LocalTextFormat.current.sizeLarge,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    FrontendInternshipTheme {
        RoundedButton(
            buttonText = "ABC",
            onButtonPress = {},
        )
    }
}

