package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.utils.PaymentTypeEnum
import com.example.frontendinternship.utils.StatusEnum
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun TransactionEntity.toDomain(): TransactionModel {
    return TransactionModel(
        transactionId = this.transactionId.toUUID(),
        total = this.total,
        subtotal = this.subtotal,
        status = StatusEnum.fromString(this.status) ?: StatusEnum.ACTIVE,
        paymentType = PaymentTypeEnum.fromString(this.paymentType) ?: PaymentTypeEnum.COUPON,
        createdAt = this.createdAt,
        authCode = this.authCode ?: "NO_AUTH",
        currency = this.currency,
    )
}

fun TransactionModel.toData(deviceId: UUID): TransactionEntity {
    return TransactionEntity(
        transactionId = this.transactionId?.toBytes() ?: UUID.randomUUID().toBytes(),
        total = this.total,
        subtotal = this.subtotal,
        status = this.status.value,
        paymentType = this.paymentType.value,
        createdAt = this.createdAt,
        authCode = this.authCode,
        currency = this.currency,
        deviceId = deviceId.toBytes(),
    )
}
