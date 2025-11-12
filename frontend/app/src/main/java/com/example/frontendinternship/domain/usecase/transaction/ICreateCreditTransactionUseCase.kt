package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState

interface ICreateCreditTransactionUseCase {
    suspend operator fun invoke(paymentState: PaymentState, creditPaymentState: CreditPaymentState)
}
