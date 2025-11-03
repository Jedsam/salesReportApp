package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FIRMWARE")
data class FirmwareEntity(
    @PrimaryKey
    @ColumnInfo(name = "firmware_id") val firmwareId: ByteArray,
    @ColumnInfo(name = "firmware_version") val firmwareVersion: String
)
