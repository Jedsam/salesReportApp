package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.CashPaymentEntity
import com.example.frontendinternship.data.model.CouponPaymentEntity
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionWithItemsAndProducts
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import com.example.frontendinternship.utils.PaymentTypeEnum
import com.example.frontendinternship.utils.StatusEnum
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun CreditPaymentState.toData(
    transactionId: ByteArray,
    authCode: String,
    cardScheme: String,
): CreditPaymentEntity {
    return CreditPaymentEntity(
        transactionId = transactionId,
        cardLast4 = cardNumber.takeLast(4),
        authCode = authCode,
        cardScheme = cardScheme
    )
}

fun CouponPaymentState.toData(
    transactionId: ByteArray,
): CouponPaymentEntity {
    return CouponPaymentEntity(
        transactionId = transactionId,
        couponCode = this.couponCode,
        couponValue = this.couponValue,
        expiryDate = this.expiryDate,
    )
}

fun CashPaymentState.toData(transactionId: ByteArray, transactionMoney: Double): CashPaymentEntity {
    return CashPaymentEntity(
        transactionId = transactionId,
        receivedAmount = this.receivedAmount,
        changeGiven = this.calculateChange(transactionMoney)
    )
}
