package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionItemEntity

@Dao
interface TransactionItemDao {


    @Insert
    suspend fun insert(transactionItemEntity: TransactionItemEntity)

    @Insert
    suspend fun insertAll(transactionItemEntities: List<TransactionItemEntity>)

    @Query("SELECT * FROM TRANSACTION_ITEMS WHERE transaction_id = :id")
    suspend fun getByTransactionId(id: ByteArray): List<TransactionItemEntity>

    @Query("SELECT * FROM TRANSACTION_ITEMS WHERE transaction_id IN (:transactionIds)")
    suspend fun getByTransactionId(transactionIds: List<ByteArray>): List<TransactionItemEntity>

    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
