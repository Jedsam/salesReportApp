package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.frontendinternship.data.model.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM PRODUCTS")
    fun getAll(): List<ProductEntity>


    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
