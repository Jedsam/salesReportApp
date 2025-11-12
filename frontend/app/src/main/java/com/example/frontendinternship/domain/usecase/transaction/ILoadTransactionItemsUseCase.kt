package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionWithItemModel

interface ILoadTransactionItemsUseCase {
    suspend operator fun invoke(transactionId: String): TransactionWithItemModel
}
