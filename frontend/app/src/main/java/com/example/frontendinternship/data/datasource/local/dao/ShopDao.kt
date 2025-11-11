package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ShopEntity

@Dao
interface ShopDao {
    @Query("SELECT * FROM SHOPS")
    suspend fun getAll(): List<ShopEntity>

    @Update
    suspend fun editOne(shop: ShopEntity)

    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
