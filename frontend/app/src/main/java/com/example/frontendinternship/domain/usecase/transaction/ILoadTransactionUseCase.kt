package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionModel

interface ILoadTransactionUseCase {
    suspend operator fun invoke(id: String): TransactionModel?
}
