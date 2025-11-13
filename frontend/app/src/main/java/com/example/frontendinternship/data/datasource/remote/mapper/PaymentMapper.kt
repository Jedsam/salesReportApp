package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.CashPaymentMethod
import com.backend.proto.sync.CouponPaymentMethod
import com.backend.proto.sync.CreditPaymentMethod
import com.backend.proto.sync.Shop
import com.example.frontendinternship.data.model.CashPaymentEntity
import com.example.frontendinternship.data.model.CouponPaymentEntity
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.google.protobuf.ByteString

fun CashPaymentEntity.toProto(): CashPaymentMethod {
    return CashPaymentMethod.newBuilder()
        .setTransactionId(ByteString.copyFrom(this.transactionId))
        .setReceivedAmount(this.receivedAmount)
        .setChangeGiven(this.changeGiven)
        .build()
}

fun CouponPaymentEntity.toProto(): CouponPaymentMethod {
    return CouponPaymentMethod.newBuilder()
        .setTransactionId(ByteString.copyFrom(this.transactionId))
        .setCouponCode(this.couponCode)
        .setCouponValue(this.couponValue)
        .setExpiryDate(this.expiryDate)
        .build()
}

fun CreditPaymentEntity.toProto(): CreditPaymentMethod {
    return CreditPaymentMethod.newBuilder()
        .setTransactionId(ByteString.copyFrom(this.transactionId))
        .setCardScheme(this.cardScheme)
        .setCardLast4(this.cardLast4)
        .setAuthCode(this.authCode)
        .build()
}
