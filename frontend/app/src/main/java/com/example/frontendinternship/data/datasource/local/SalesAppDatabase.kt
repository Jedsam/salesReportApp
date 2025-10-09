package com.example.frontendinternship.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ReceiptDao
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ReceiptEntity

class SalesAppDatabase {
    // @TypeConverters() add type converters in the future?
    @Database(entities = [ReceiptEntity::class, ProductEntity::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun receiptDao(): ReceiptDao
        abstract fun productDao(): ProductDao
    }
}