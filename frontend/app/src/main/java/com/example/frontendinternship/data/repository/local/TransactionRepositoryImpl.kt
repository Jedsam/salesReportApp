package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.TransactionRepository
import jakarta.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun loadTransactions(): List<TransactionModel> {
        val transactionEntities: List<TransactionEntity> = transactionDao.getAll()
        return transactionEntities.map { transactionEntity: TransactionEntity ->
            transactionEntity.toDomain()
        }
    }
}

