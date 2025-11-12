package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.TransactionItemModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.domain.repository.TransactionItemRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import javax.inject.Inject

class CreateCouponTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ICreateCouponTransactionUseCase {
    override suspend fun invoke(
        paymentState: PaymentState,
        couponPaymentState: CouponPaymentState
    ) {

    }
}