package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TRANSACTION_ITEMS",
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["transaction_id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["product_id"],
            childColumns = ["product_id"]
        )
    ],
    indices = [Index("transaction_id"), Index("product_id")]
)
data class TransactionItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_item_id") val transactionItemId: ByteArray,
    @ColumnInfo(name = "transaction_id") val transactionId: ByteArray,
    @ColumnInfo(name = "product_id") val productId: ByteArray,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "sku") val sku: String,
    @ColumnInfo(name = "unit_price") val unitPrice: Double,
    @ColumnInfo(name = "quantity") val quantity: Double,
    @ColumnInfo(name = "vat_rate") val vatRate: Double,
    @ColumnInfo(name = "total") val total: Double
)

