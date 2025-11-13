package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.domain.repository.TransactionItemRepository
import jakarta.inject.Inject

class TransactionItemRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionItemDao
) : TransactionItemRepository {


}

