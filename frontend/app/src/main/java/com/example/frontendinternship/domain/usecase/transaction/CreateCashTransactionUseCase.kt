package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import javax.inject.Inject

class CreateCashTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ICreateCashTransactionUseCase {
    override suspend fun invoke(
        paymentState: PaymentState,
        cashPaymentState: CashPaymentState,
        productBasket: List<ProductWithCount>
    ) {
        transactionRepository.createCashTransaction(paymentState, cashPaymentState, productBasket)
    }
}