package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MODEL")
data class ModelEntity(
    @PrimaryKey
    @ColumnInfo(name = "model_id") val modelId: ByteArray,
    @ColumnInfo(name = "model_name") val modelName: String
)
