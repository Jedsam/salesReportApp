package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.frontendinternship.data.model.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE vatrate = (:vatValue)")
    fun loadAllByVat(vatValue:Int ): List<ProductEntity>

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)
}
