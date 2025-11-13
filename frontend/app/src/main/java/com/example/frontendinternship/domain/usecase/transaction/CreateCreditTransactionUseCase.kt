package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import javax.inject.Inject

class CreateCreditTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ICreateCreditTransactionUseCase {
    override suspend fun invoke(
        paymentState: PaymentState,
        creditPaymentState: CreditPaymentState,
        productBasket: List<ProductWithCount>
    ) {
        transactionRepository.createCreditTransaction(
            paymentState,
            creditPaymentState,
            productBasket,
            authCode = "mycode",
            cardScheme = "myscheme",
        )
    }
}