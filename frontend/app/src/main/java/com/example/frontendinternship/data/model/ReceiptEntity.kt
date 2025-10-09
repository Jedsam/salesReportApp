package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipt")
data class ReceiptEntity(
    @PrimaryKey(autoGenerate = true)
    val receiptNumber: Int? = null,
    @ColumnInfo(name = "receiptDateTime") val receiptDateTime: String?,
    @ColumnInfo(name = "amountVat0") val amountVat0: String?,
    @ColumnInfo(name = "amountVat1") val amountVat1: String?,
    @ColumnInfo(name = "amountVat10") val amountVat10: String?,
    @ColumnInfo(name = "amountVat20") val amountVat20: String?,
    @ColumnInfo(name = "paymentType") val paymentType: Int?,
)


