package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.PaymentState

interface ICreateCouponTransactionUseCase {
    suspend operator fun invoke(paymentState: PaymentState, couponPaymentState: CouponPaymentState)
}
