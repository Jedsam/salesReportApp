package com.example.frontendinternship.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.frontendinternship.data.local.entity.Product

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
