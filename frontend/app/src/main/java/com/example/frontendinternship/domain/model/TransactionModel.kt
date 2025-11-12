package com.example.frontendinternship.domain.model

import android.icu.text.SimpleDateFormat
import com.example.frontendinternship.utils.PaymentTypeEnum
import com.example.frontendinternship.utils.StatusEnum
import java.util.Date
import java.util.Locale
import java.util.UUID

data class TransactionModel(
    val transactionId: UUID? = UUID.randomUUID(),
    var subtotal: Double = 0.0,
    var total: Double = 0.0,
    val currency: String = "TL",
    val authCode: String = "NO_CODE",
    val createdAt: String = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis())),
    val status: StatusEnum = StatusEnum.ACTIVE,
    val paymentType: PaymentTypeEnum = PaymentTypeEnum.CREDIT,
)
