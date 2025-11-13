package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import javax.inject.Inject

class CreateCouponTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ICreateCouponTransactionUseCase {
    override suspend fun invoke(
        paymentState: PaymentState,
        couponPaymentState: CouponPaymentState,
        productBasket: List<ProductWithCount>
    ) {
        transactionRepository.createCouponTransaction(
            paymentState,
            couponPaymentState,
            productBasket
        )
    }
}