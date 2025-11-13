package com.example.frontendinternship.domain.usecase.transaction

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.ui.states.PaymentState

interface ICreateCancelTransactionUseCase {
    suspend operator fun invoke(paymentState: PaymentState, productBasket: List<ProductWithCount>)
}
