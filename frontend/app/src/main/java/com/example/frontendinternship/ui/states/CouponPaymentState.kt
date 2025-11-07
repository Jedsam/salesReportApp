package com.example.frontendinternship.ui.states

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CouponPaymentState(
    val couponCode: String = "",
    val couponValue: Double = 0.0,
    val expiryDate: String = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis())),
)

