package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.utils.toBytes
import jakarta.inject.Inject
import java.util.UUID

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun loadTransactions(): List<TransactionModel> {
        val transactionEntities: List<TransactionEntity> = transactionDao.getAll()
        return transactionEntities.map { transactionEntity: TransactionEntity ->
            transactionEntity.toDomain()
        }
    }

    override suspend fun loadTransaction(id: String): TransactionModel? {
        return transactionDao.getById(
            UUID.fromString(id).toBytes()
        )?.toDomain()
    }

    override suspend fun getTransactionWithItemsAndProducts(id: String): TransactionWithItemModel? {
        return transactionDao.getTransactionWithItemsAndProducts(UUID.fromString(id).toBytes()).toDomain()
    }

}

