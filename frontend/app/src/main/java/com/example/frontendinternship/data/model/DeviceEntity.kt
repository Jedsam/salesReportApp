package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.frontendinternship.utils.StatusEnum

@Entity(
    tableName = "DEVICES",
    foreignKeys = [
        ForeignKey(
            entity = ShopEntity::class,
            parentColumns = ["shop_id"],
            childColumns = ["shop_id"]
        ),
        ForeignKey(
            entity = FirmwareEntity::class,
            parentColumns = ["firmware_id"],
            childColumns = ["firmware_id"]
        ),
        ForeignKey(
            entity = ModelEntity::class,
            parentColumns = ["model_id"],
            childColumns = ["model_id"]
        )
    ],
    indices = [Index("shop_id"), Index("firmware_id"), Index("model_id")]
)

data class DeviceEntity(
    @PrimaryKey
    @ColumnInfo(name = "device_id") val deviceId: ByteArray,
    @ColumnInfo(name = "shop_id") val shopId: ByteArray,
    @ColumnInfo(name = "firmware_id") val firmwareId: ByteArray,
    @ColumnInfo(name = "model_id") val modelId: ByteArray,
    @ColumnInfo(name = "last_seen") val lastSeen: String?,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "status", defaultValue = "active") val status: String
)