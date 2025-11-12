package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionItemModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.domain.repository.TransactionItemRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import javax.inject.Inject

class LoadTransactionItemsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ILoadTransactionItemsUseCase {
    override suspend fun invoke(transactionId: String): TransactionWithItemModel {
        return transactionRepository.getTransactionWithItemsAndProducts(transactionId) ?: TransactionWithItemModel()
    }
}