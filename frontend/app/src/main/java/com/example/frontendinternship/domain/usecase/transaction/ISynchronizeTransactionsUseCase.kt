package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionModel

interface ISynchronizeTransactionsUseCase {
    suspend operator fun invoke(forceSync: Boolean): Boolean
}