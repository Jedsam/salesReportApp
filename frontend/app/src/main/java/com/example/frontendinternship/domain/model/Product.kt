package com.example.frontendinternship.domain.model

import androidx.room.ColumnInfo

data class Product(
    val id: Int = 0,
    val productName: String = "NONAME",
    val vatRate: Int = 0,
    var price: Float = 0f,
)
