package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.PaymentState
import javax.inject.Inject

class CreateCancelTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ICreateCancelTransactionUseCase {
    override suspend fun invoke(paymentState: PaymentState, productBasket: List<ProductWithCount>) {
        transactionRepository.createCancelTransaction(paymentState, productBasket)
    }
}