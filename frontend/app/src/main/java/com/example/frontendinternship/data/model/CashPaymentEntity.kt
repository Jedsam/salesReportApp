package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "CASH_PAYMENT_METHOD",
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["transaction_id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CashPaymentEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id") val transactionId: ByteArray,
    @ColumnInfo(name = "received_amount") val receivedAmount: Double,
    @ColumnInfo(name = "change_given") val changeGiven: Double
)
