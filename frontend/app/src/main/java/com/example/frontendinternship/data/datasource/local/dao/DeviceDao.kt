package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.DeviceEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ShopEntity

@Dao
interface DeviceDao {
    @Query("SELECT * FROM DEVICES LIMIT 1")
    suspend fun getCurrent(): DeviceEntity


    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
