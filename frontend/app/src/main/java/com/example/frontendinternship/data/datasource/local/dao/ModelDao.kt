package com.example.frontendinternship.data.datasource.local.dao

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.frontendinternship.data.model.ModelEntity

import com.example.frontendinternship.data.model.ReceiptEntity
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost

import kotlin.collections.List

@Dao
interface ModelDao {
    @Query("SELECT * FROM MODEL WHERE model_id = (:modelId)")
    suspend fun getOne(modelId: ByteArray): ModelEntity
}
