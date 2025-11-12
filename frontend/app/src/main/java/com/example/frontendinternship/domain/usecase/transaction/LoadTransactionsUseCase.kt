package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.domain.usecase.product.ILoadProductsUseCase
import javax.inject.Inject

class LoadTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ILoadTransactionsUseCase {
    override suspend fun invoke(): List<TransactionModel> {
        return transactionRepository.loadTransactions()
    }
}