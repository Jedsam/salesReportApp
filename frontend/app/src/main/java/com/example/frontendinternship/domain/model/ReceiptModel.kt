package com.example.frontendinternship.domain.model

data class ReceiptModel(
    val receiptNumber: Int,
    val receiptDateTime: java.util.Date,
    val amountVat0: Float,
    val amountVat1: Float,
    val amountVat10: Float,
    val amountVat20: Float,
    val paymentType: Int,
)
