package com.example.frontendinternship.domain.model

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class ProductModel(
    val id: UUID? = UUID.randomUUID(),
    val productName: String = "NONAME",
    var price: Double = 0.0,
    val vatRate: Double = 0.0,
    val created: String = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis())),
)
