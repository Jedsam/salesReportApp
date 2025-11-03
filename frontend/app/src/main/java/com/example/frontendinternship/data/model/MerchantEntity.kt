package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "MERCHANTS",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("user_id")]
)
data class MerchantEntity(
    @PrimaryKey
    @ColumnInfo(name = "merchant_id") val merchantId: ByteArray,
    @ColumnInfo(name = "user_id") val userId: ByteArray,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "business_name") val businessName: String,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "status") val status: String
)
