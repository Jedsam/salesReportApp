package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PRODUCTS")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_id") val productId: ByteArray,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "vat_rate") val vatRate: Double,
    @ColumnInfo(name = "is_deleted") val isDeleted: Boolean,
    @ColumnInfo(name = "created") val created: String
)

