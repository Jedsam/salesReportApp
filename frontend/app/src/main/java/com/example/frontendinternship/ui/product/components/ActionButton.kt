package com.example.frontendinternship.ui.product.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.common.shapes.TriangleCutCornerShape
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.ui.theme.LocalTextFormat

@Composable
fun GetActionButton(
    text: String,
    //receiptDao: ReceiptDao?,
    basketListState: MutableState<List<ProductWithCount>>,
    totalBasketPriceState: MutableFloatState,
    paymentMethod: PAYMENT_METHOD,
    //reportReceipt: ReportReceiptViewModel?
) {
    var basketList by basketListState
    var totalBasketPrice by totalBasketPriceState
    Button(
        onClick = {
            //receiptDao?.insert(basketList, paymentMethod)
            basketList = emptyList()
            totalBasketPrice = 0f
            //reportReceipt?.checkAndReportBasket(basketList)
        },
        border = BorderStroke(
            width = 2.dp,            // Thickness of the border
            color = Color.Black        // Color of the border
        ),
        modifier = Modifier
            .size(
                width = LocalDimensions.current.viewLargePlus,
                height = LocalDimensions.current.viewBig
            ),
        // TODO Add color change when button is pressed
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Black,
            disabledContainerColor = Color.Blue,
            disabledContentColor = Color.Black,
        ),
        shape = TriangleCutCornerShape(16f),
        contentPadding = PaddingValues(horizontal = LocalPadding.current.VeryTiny, vertical = LocalPadding.current.Mini)
    ) {
        Text(modifier = Modifier.fillMaxWidth(),
            text = text, maxLines = 2,
            lineHeight = LocalTextFormat.current.sizeNormal,
            autoSize = TextAutoSize.StepBased(
                minFontSize = LocalTextFormat.current.sizeTiny,
                maxFontSize = LocalTextFormat.current.sizeNormal,
                stepSize = 1.sp
            ), textAlign = TextAlign.Center, color = Color.Black
        )
    }
}
