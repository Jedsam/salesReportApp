package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.PaymentState

interface ICreateCashTransactionUseCase {
    suspend operator fun invoke(paymentState: PaymentState, cashPaymentState: CashPaymentState)
}
