package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM USERS LIMIT 1")
    suspend fun getCurrent(): UserEntity

    @Update
    suspend fun editOne(userEntity: UserEntity)

    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
