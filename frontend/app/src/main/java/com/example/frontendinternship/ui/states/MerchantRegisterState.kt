package com.example.frontendinternship.ui.states

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class MerchantRegisterState(
    val merchantId: UUID? = UUID.randomUUID(),
    val name: String = "NO_NAME",
    val businessName: String = "NO_BUSINESS_NAME",
    val phone: String? = "12345678",
    val address: String? = "NO_ADDRESS",
    val createdAt: String = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis())),
    val status: String = "active",
    val username: String = "",
    val password: String = "",
)
