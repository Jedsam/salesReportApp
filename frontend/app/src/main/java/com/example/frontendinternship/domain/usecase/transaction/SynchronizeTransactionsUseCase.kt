package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.repository.SynchronizeRepository
import javax.inject.Inject

class SynchronizeTransactionsUseCase @Inject constructor(
    private val synchronizeRepository: SynchronizeRepository
) : ISynchronizeTransactionsUseCase {
    override suspend fun invoke(): Boolean {
        return synchronizeRepository.synchronize()
    }
}