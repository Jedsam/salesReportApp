package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.ReceiptEntity
import com.example.frontendinternship.domain.model.ReceiptModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun ReceiptEntity.toDomain(): ReceiptModel {
    return ReceiptModel(
        this.receiptNumber ?: -1,
        SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault()).parse(
            this.receiptDateTime ?: "0-01-1"
        ) ?: Date(
            0
        ),
        this.amountVat0?.toFloatOrNull() ?: 0f,
        this.amountVat1?.toFloatOrNull() ?: 0f,
        this.amountVat10?.toFloatOrNull() ?: 0f,
        this.amountVat20?.toFloatOrNull() ?: 0f,
        this.paymentType ?: -1
    )
}