package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.repository.SynchronizeRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import javax.inject.Inject

class SynchronizeTransactionsUseCase @Inject constructor(
    private val synchronizeRepository: SynchronizeRepository,
    private val transactionRepository: TransactionRepository,
) : ISynchronizeTransactionsUseCase {
    override suspend fun invoke(): Boolean {
        return synchronizeRepository.synchronize(transactionRepository.loadTransactions())
    }
}