package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionModel

interface ILoadTransactionsUseCase {
    suspend operator fun invoke(): List<TransactionModel>
}
