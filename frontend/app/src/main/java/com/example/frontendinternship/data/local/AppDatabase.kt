package com.example.frontendinternship.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frontendinternship.data.local.dao.ProductDao
import com.example.frontendinternship.data.local.dao.ReceiptDao
import com.example.frontendinternship.data.local.entity.Product
import com.example.frontendinternship.data.local.entity.Receipt

class AppDatabase {
    @Database(entities = [Receipt::class, Product::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun receiptDao(): ReceiptDao
        abstract fun productDao(): ProductDao
    }
}