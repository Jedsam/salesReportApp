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


@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") val productName: String?,
    @ColumnInfo(name = "vatRate") val vatRate: Int?,
    @ColumnInfo(name = "price") val price: String?,
)

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE vatrate = (:vatValue)")
    fun loadAllByVat(vatValue:Int ): List<Product>

    @Insert
    fun insertAll(vararg products: Product)

    @Delete
    fun delete(product: Product)
}
@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
