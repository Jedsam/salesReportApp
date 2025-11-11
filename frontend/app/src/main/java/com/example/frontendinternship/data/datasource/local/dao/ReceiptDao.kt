package com.example.frontendinternship.data.datasource.local.dao

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.example.frontendinternship.data.model.ReceiptEntity
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost

import kotlin.collections.List

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipt")
    suspend fun getAll(): List<ReceiptEntity>


    @Insert
    suspend fun insert(receipt: ReceiptEntity)


    @Insert
    suspend fun insertAll(vararg receipts: ReceiptEntity)

    @Delete
    suspend fun delete(receipt: ReceiptEntity)

    @Query("SELECT * FROM receipt WHERE receiptDateTime = (:dateTime)")
    suspend fun getFromDate(dateTime: String): List<ReceiptEntity>
}
