package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionWithItemsAndProducts

@Dao
interface TransactionDao {
    @Query("SELECT * FROM TRANSACTIONS")
    suspend fun getAll(): List<TransactionEntity>

    @Query("SELECT * FROM TRANSACTIONS WHERE transaction_id = :id")
    suspend fun getById(id: ByteArray): TransactionEntity?

    @Transaction
    @Query("SELECT * FROM TRANSACTIONS WHERE transaction_id = :id")
    suspend fun getTransactionWithItemsAndProducts(id: ByteArray): TransactionWithItemsAndProducts

    @Insert
    suspend fun insert(transactions: TransactionEntity)

    @Insert
    suspend fun addAll(transactions: List<TransactionEntity>)

    //@Insert
    //fun insertAll(vararg PRODUCTSs: ProductEntity)

    //@Delete
    //fun delete(PRODUCTS: ProductEntity)
}
