package com.example.frontendinternship.domain.model

import androidx.room.ColumnInfo

data class Product(val id: Int,
val productName: String,
val vatRate: Int,
var price: String,
)
