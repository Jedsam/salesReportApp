package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "COUPON_PAYMENT_METHOD",
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["transaction_id"],
            childColumns = ["transaction_id"],
        )
    ]
)
data class CouponPaymentEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id") val transactionId: ByteArray,
    @ColumnInfo(name = "coupon_code") val couponCode: String,
    @ColumnInfo(name = "coupon_value") val couponValue: Double,
    @ColumnInfo(name = "expiry_date") val expiryDate: String
)
