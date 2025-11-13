package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Transaction
import com.example.frontendinternship.data.model.TransactionEntity
import com.google.protobuf.ByteString

fun TransactionEntity.toProto(): Transaction {
    return Transaction.newBuilder()
        .setTransactionId(ByteString.copyFrom(this.transactionId))
        .setDeviceId(ByteString.copyFrom(this.deviceId))
        .setSubtotal(this.subtotal)
        .setTotal(this.total)
        .setCurrency(this.currency)
        .setAuthCode(this.authCode)
        .setCreatedAt(this.createdAt)
        .setStatus(this.status)
        .setPaymentType(this.paymentType)
        .build()
}
