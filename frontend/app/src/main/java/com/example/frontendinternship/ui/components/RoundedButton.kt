package com.example.frontendinternship.ui.components

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun RoundedButton(
    buttonText: String,
    textSize: TextUnit = LocalTextFormat.current.sizeNormal,
    onButtonPress: () -> Unit,
    modifier: Modifier,
    roundness: Int = 25,
    borderColor: Color = Color.Blue,
    containerColor: Color = Color.Blue,
    contentColor: Color = Color.White,
) {
    Row() {
        Button(
            onClick = onButtonPress,
            modifier = modifier,
            shape = RoundedCornerShape(roundness),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            border = BorderStroke(LocalPadding.current.VeryMini, borderColor)
        ) {
            Text(
                text = buttonText,
                fontSize = textSize,
            )
        }
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    FrontendInternshipTheme {
        RoundedButton(
            buttonText = "ABC",
            onButtonPress = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
fun RoundedButtonPreviewWithBorder() {
    FrontendInternshipTheme {
        RoundedButton(
            buttonText = "ABC",
            onButtonPress = {},
            modifier = Modifier,
            borderColor = Color.Red,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Red
        )
    }
}
