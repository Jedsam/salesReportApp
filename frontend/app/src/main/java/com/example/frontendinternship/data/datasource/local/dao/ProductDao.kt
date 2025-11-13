package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM PRODUCTS WHERE is_deleted = 0")
    suspend fun getAllNotDeleted(): List<ProductEntity>

    @Query("SELECT * FROM PRODUCTS")
    suspend fun getAll(): List<ProductEntity>

    @Query("UPDATE products SET is_deleted = 1 WHERE product_id = :uuid")
    suspend fun markDeleted(uuid: ByteArray)

    @Update
    suspend fun editAll(products: List<ProductEntity>)

    @Insert
    suspend fun addAll(products: List<ProductEntity>)
    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
