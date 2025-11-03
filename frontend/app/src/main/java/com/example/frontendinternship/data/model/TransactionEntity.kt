package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TRANSACTIONS",
    foreignKeys = [
        ForeignKey(
            entity = DeviceEntity::class,
            parentColumns = ["device_id"],
            childColumns = ["device_id"]
        )
    ],
    indices = [Index("device_id")]
)
data class TransactionEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id") val transactionId: ByteArray,
    @ColumnInfo(name = "device_id") val deviceId: ByteArray,
    @ColumnInfo(name = "subtotal") val subtotal: Double,
    @ColumnInfo(name = "total") val total: Double,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "auth_code") val authCode: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "payment_type") val paymentType: String
)

