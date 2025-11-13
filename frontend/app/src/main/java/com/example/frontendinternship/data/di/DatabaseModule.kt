package com.example.frontendinternship.data.di

import android.content.Context
import androidx.room.Room
import com.example.frontendinternship.data.datasource.local.SalesAppDatabase
import com.example.frontendinternship.data.datasource.local.dao.DeviceDao
import com.example.frontendinternship.data.datasource.local.dao.MerchantDao
import com.example.frontendinternship.data.datasource.local.dao.PaymentDao
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ReceiptDao
import com.example.frontendinternship.data.datasource.local.dao.ShopDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.data.datasource.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class DatabaseModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext applicationContext: Context): SalesAppDatabase.AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                SalesAppDatabase.AppDatabase::class.java, "PRODUCTS"
            )
                .createFromAsset("retail.db")
                .build()
        }

        @Provides
        fun provideProductDao(appDatabase: SalesAppDatabase.AppDatabase): ProductDao {
            return appDatabase.productDao()
        }

        @Provides
        fun provideReceiptDao(appDatabase: SalesAppDatabase.AppDatabase): ReceiptDao {
            return appDatabase.receiptDao()
        }

        @Provides
        fun provideShopDao(appDatabase: SalesAppDatabase.AppDatabase): ShopDao {
            return appDatabase.shopDao()
        }

        @Provides
        fun provideUserDao(appDatabase: SalesAppDatabase.AppDatabase): UserDao {
            return appDatabase.userDao()
        }

        @Provides
        fun provideMerchantDao(appDatabase: SalesAppDatabase.AppDatabase): MerchantDao {
            return appDatabase.merchantDao()
        }

        @Provides
        fun provideTransactionDao(appDatabase: SalesAppDatabase.AppDatabase): TransactionDao {
            return appDatabase.transactionDao()
        }

        @Provides
        fun provideDeviceDao(appDatabase: SalesAppDatabase.AppDatabase): DeviceDao {
            return appDatabase.deviceDao()
        }

        @Provides
        fun providePaymentDao(appDatabase: SalesAppDatabase.AppDatabase): PaymentDao {
            return appDatabase.paymentDao()
        }

        @Provides
        fun provideTransactionItemDao(appDatabase: SalesAppDatabase.AppDatabase): TransactionItemDao {
            return appDatabase.transactionItemDao()
        }


    }
}