package com.example.frontendinternship.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalPadding

@Composable
fun RoundedTextField(
    textFieldInformation: String,
    textValue: String,
    onFieldValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    textFieldModifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    placeholderText: String = "Enter your value",
) {
    Column {
        Text(
            text = textFieldInformation,
            color = textColor,
        )
        Spacer(modifier = Modifier.height(LocalPadding.current.VeryTiny))
        BasicTextField(
            value = textValue,
            onValueChange = { newText: String -> onFieldValueChange(newText) },
            modifier = textFieldModifier
                .border(
                    shape = RoundedCornerShape(25),
                    width = 2.dp,
                    color = Color.Black
                )
                .padding(LocalPadding.current.Small),
            decorationBox = { innerTextField ->
                if (textValue.isEmpty()) {
                    Text(text = placeholderText, color = Color.Gray)
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        )
    }
}

@Preview
@Composable
fun RoundedTextFieldPreview() {
    FrontendInternshipTheme {
        RoundedTextField(
            textFieldInformation = "MyTextField",
            textValue = "ABC",
            onFieldValueChange = {},
            KeyboardType.Number,
        )
    }
}

