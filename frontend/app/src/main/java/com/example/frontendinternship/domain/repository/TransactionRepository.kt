package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState


interface TransactionRepository {
    suspend fun loadTransactions(): List<TransactionModel>
    suspend fun loadTransaction(id: String): TransactionModel?
    suspend fun getTransactionWithItemsAndProducts(id: String): TransactionWithItemModel?
    suspend fun createCancelTransaction(
        paymentState: PaymentState,
        productBasket: List<ProductWithCount>
    )

    suspend fun createCashTransaction(
        paymentState: PaymentState,
        cashPaymentState: CashPaymentState,
        productBasket: List<ProductWithCount>
    )

    suspend fun createCreditTransaction(
        paymentState: PaymentState,
        creditPaymentState: CreditPaymentState,
        productBasket: List<ProductWithCount>,
        authCode: String,
        cardScheme: String,
    )

    suspend fun createCouponTransaction(
        paymentState: PaymentState,
        couponPaymentState: CouponPaymentState,
        productBasket: List<ProductWithCount>
    )

}
