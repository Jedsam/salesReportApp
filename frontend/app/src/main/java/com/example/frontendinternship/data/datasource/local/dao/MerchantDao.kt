package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.MerchantEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.example.frontendinternship.data.model.UserEntity

@Dao
interface MerchantDao {

    @Query("SELECT * FROM MERCHANTS")
    suspend fun getAll(): List<MerchantEntity>

    @Update
    suspend fun editOne(merchant: MerchantEntity)

    @Query("SELECT * FROM MERCHANTS LIMIT 1")
    suspend fun getCurrent(): MerchantEntity

    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
