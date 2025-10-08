package com.example.frontendinternship.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query


@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") val productName: String?,
    @ColumnInfo(name = "vatRate") val vatRate: Int?,
    @ColumnInfo(name = "price") var price: String?,
)

