package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel


interface TransactionRepository {
    suspend fun loadTransactions(): List<TransactionModel>
    suspend fun loadTransaction(id: String): TransactionModel?
    suspend fun getTransactionWithItemsAndProducts(id: String): TransactionWithItemModel?

}
