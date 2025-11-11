package com.example.frontendinternship.domain.model

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class ShopModel(
    val shopId: UUID = UUID.randomUUID(),
    val name: String = "NO_NAME",
    val address: String = "NO_ADDRESS",
    val phone: String? = "NO_PHONE",
    val email: String? = "NO_EMAIL",
    val createdAt: String = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis())),
    val status: String = "active",
)
