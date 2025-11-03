package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "CREDIT_PAYMENT_METHOD",
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["transaction_id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CreditPaymentEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id") val transactionId: ByteArray,
    @ColumnInfo(name = "card_scheme") val cardScheme: String,
    @ColumnInfo(name = "card_last4") val cardLast4: String,
    @ColumnInfo(name = "auth_code") val authCode: String
)
