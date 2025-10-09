package com.example.frontendinternship.data.di

import android.content.Context
import androidx.room.Room
import com.example.frontendinternship.data.datasource.local.SalesAppDatabase
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ReceiptDao
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
            // --- Your database setup code goes here ---
            return Room.databaseBuilder(
                applicationContext,
                SalesAppDatabase.AppDatabase::class.java, "product"
            )
                .createFromAsset("retail_2.db")
                // WARNING: Generally avoid allowMainThreadQueries() in production
                .allowMainThreadQueries()
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
    }
}