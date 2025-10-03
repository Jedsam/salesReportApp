package com.example.frontendinternship

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity
data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val productName: String,
    @ColumnInfo(name = "vatRate") val vatRate: Int,
    @ColumnInfo(name = "price") val price: Double,
)

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE vatrate = (:vatValue)")
    suspend fun loadAllByVat(vatValue:Int ): List<Product>

    @Insert
    suspend fun insertAll(vararg products: Product)

    @Delete
    suspend fun delete(product: Product)
}
@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
