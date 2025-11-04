package com.example.frontendinternship.domain.model

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.frontendinternship.data.model.UserEntity
import java.util.Date
import java.util.Locale
import java.util.UUID

data class MerchantModel(
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
)
