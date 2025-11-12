package com.example.frontendinternship.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Transaction
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import java.util.UUID

data class TransactionItemModel(
    val transactionItemId: UUID? = UUID.randomUUID(),
    val product: ProductModel = ProductModel(),
    val productName: String = "PRODUCT_NAME",
    val sku: String = "a/a/a/a",
    val unitPrice: Double = 0.0,
    val quantity: Double = 0.0,
    val vatRate: Double = 0.0,
    val total: Double = 0.0,
)

