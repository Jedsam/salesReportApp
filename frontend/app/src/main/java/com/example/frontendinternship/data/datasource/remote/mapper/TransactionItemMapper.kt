package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Transaction
import com.backend.proto.sync.TransactionItem
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionItemEntity
import com.google.protobuf.ByteString

fun TransactionItemEntity.toProto(): TransactionItem {
    return TransactionItem.newBuilder()
        .setTransactionId(ByteString.copyFrom(this.transactionId))
        .setTransactionItemId(ByteString.copyFrom(this.transactionItemId))
        .setProductId(ByteString.copyFrom(this.productId))
        .setProductName(this.productName)
        .setUnitPrice(this.unitPrice)
        .setQuantity(this.quantity)
        .setVatRate(this.vatRate)
        .setTotal(this.total)
        .build()
}
