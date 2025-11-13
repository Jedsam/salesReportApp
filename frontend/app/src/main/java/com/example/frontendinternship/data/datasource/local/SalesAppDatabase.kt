package com.example.frontendinternship.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frontendinternship.data.datasource.local.dao.DeviceDao
import com.example.frontendinternship.data.datasource.local.dao.FirmwareDao
import com.example.frontendinternship.data.datasource.local.dao.MerchantDao
import com.example.frontendinternship.data.datasource.local.dao.ModelDao
import com.example.frontendinternship.data.datasource.local.dao.PaymentDao
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ReceiptDao
import com.example.frontendinternship.data.datasource.local.dao.ShopDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.data.datasource.local.dao.UserDao
import com.example.frontendinternship.data.model.CashPaymentEntity
import com.example.frontendinternship.data.model.CouponPaymentEntity
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.DeviceEntity
import com.example.frontendinternship.data.model.FirmwareEntity
import com.example.frontendinternship.data.model.MerchantEntity
import com.example.frontendinternship.data.model.ModelEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ReceiptEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionItemEntity
import com.example.frontendinternship.data.model.UserEntity

class SalesAppDatabase {
    // @TypeConverters() add type converters in the future?
    @Database(
        entities = [
            ReceiptEntity::class,
            ProductEntity::class,
            ShopEntity::class,
            UserEntity::class,
            DeviceEntity::class,
            FirmwareEntity::class,
            MerchantEntity::class,
            TransactionEntity::class,
            TransactionItemEntity::class,
            ModelEntity::class,
            CashPaymentEntity::class,
            CouponPaymentEntity::class,
            CreditPaymentEntity::class,
        ],
        version = 4
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun receiptDao(): ReceiptDao
        abstract fun productDao(): ProductDao
        abstract fun shopDao(): ShopDao
        abstract fun userDao(): UserDao
        abstract fun merchantDao(): MerchantDao
        abstract fun transactionDao(): TransactionDao
        abstract fun transactionItemDao(): TransactionItemDao
        abstract fun deviceDao(): DeviceDao
        abstract fun paymentDao(): PaymentDao
        abstract fun modelDao(): ModelDao
        abstract fun firmwareDao(): FirmwareDao
    }
}