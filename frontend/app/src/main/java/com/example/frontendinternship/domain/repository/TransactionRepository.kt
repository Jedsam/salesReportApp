package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.TransactionModel
import java.util.UUID


interface TransactionRepository {
    suspend fun loadTransactions(): List<TransactionModel>
}