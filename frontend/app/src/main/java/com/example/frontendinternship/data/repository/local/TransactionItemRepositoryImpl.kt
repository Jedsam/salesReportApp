package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.domain.model.TransactionItemModel
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.TransactionItemRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.utils.toBytes
import jakarta.inject.Inject
import java.util.UUID

class TransactionItemRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionItemDao
) : TransactionItemRepository {


}

