package com.example.frontendinternship.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id") val userId: ByteArray,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password_hash") val passwordHash: String
)
