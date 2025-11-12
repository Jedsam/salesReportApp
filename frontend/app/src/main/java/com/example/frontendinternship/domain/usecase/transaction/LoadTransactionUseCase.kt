package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.domain.usecase.product.ILoadProductsUseCase
import java.util.UUID
import javax.inject.Inject

class LoadTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ILoadTransactionUseCase {
    override suspend fun invoke(id: String): TransactionModel? {
        return transactionRepository.loadTransaction(id)
    }
}