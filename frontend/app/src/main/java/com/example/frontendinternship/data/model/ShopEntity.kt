package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "SHOPS",
    foreignKeys = [
        ForeignKey(
            entity = MerchantEntity::class,
            parentColumns = ["merchant_id"],
            childColumns = ["merchant_id"],
        )
    ],
    indices = [Index("merchant_id")]
)
data class ShopEntity(
    @PrimaryKey
    @ColumnInfo(name = "shop_id") val shopId: ByteArray,
    @ColumnInfo(name = "merchant_id") val merchantId: ByteArray,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "status", defaultValue = "active") val status: String
)
