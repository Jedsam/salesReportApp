package com.example.frontendinternship.domain.model

import android.icu.text.SimpleDateFormat
import androidx.room.Transaction
import com.example.frontendinternship.utils.PaymentTypeEnum
import com.example.frontendinternship.utils.StatusEnum
import java.util.Date
import java.util.Locale
import java.util.UUID

data class TransactionWithItemModel(
    val transactionItem: List<TransactionItemModel> = emptyList(),
    val transaction: TransactionModel = TransactionModel(),
)
