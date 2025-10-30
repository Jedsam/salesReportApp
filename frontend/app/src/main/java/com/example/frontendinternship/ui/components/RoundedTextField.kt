package com.example.frontendinternship.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun RoundedTextField(
    startingValue: String,
    onFieldValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
) {
    BasicTextField(
        value = startingValue,
        onValueChange = { newText: String -> onFieldValueChange(newText) },
        modifier = Modifier
            .size(
                width = LocalDimensions.current.viewLarge,
                height = LocalDimensions.current.viewSmall
            )
            .border(
                shape = RoundedCornerShape(50),
                width = 2.dp,
                color = Color.Black
            )
            .padding(LocalPadding.current.Tiny),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@Preview
@Composable
fun RoundedTextFieldPreview() {
    FrontendInternshipTheme {
        RoundedTextField(
            startingValue = "ABC",
            onFieldValueChange = {},
            KeyboardType.Number,
        )
    }
}

