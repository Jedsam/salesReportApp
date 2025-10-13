package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") val productName: String?,
    @ColumnInfo(name = "vatRate") val vatRate: Int?,
    @ColumnInfo(name = "price") var price: String?,
)
