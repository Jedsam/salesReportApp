package com.example.frontendinternship.data.datasource.local.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionItemEntity
import com.example.frontendinternship.data.model.TransactionItemWithProduct
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.TransactionItemModel
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun TransactionItemEntity.toDomain(
    product: ProductEntity,
): TransactionItemModel {
    return TransactionItemModel(
        transactionItemId = this.transactionItemId.toUUID(),
        product = product.toDomain(),
        productName = this.productName,
        unitPrice = this.unitPrice,
        quantity = this.quantity,
        vatRate = this.vatRate,
        total = this.total,
    )
}

fun TransactionItemModel.toData(transactionId: UUID): TransactionItemEntity {
    return TransactionItemEntity(
        transactionItemId = this.transactionItemId?.toBytes() ?: UUID.randomUUID().toBytes(),
        productId = this.product.productId?.toBytes() ?: UUID.randomUUID().toBytes(),
        productName = this.productName,
        unitPrice = this.unitPrice,
        quantity = this.quantity,
        vatRate = this.vatRate,
        total = this.total,
        transactionId = transactionId.toBytes()
    )
}

fun TransactionItemWithProduct.toDomain(
): TransactionItemModel {
    return TransactionItemModel(
        transactionItemId = this.item.transactionItemId.toUUID(),
        product = this.product.toDomain(),
        productName = this.item.productName,
        unitPrice = this.item.unitPrice,
        quantity = this.item.quantity,
        vatRate = this.item.vatRate,
        total = this.item.total,
    )
}

fun ProductWithCount.toData(
    transactionId: ByteArray
): TransactionItemEntity {
    val product = this.product
    return TransactionItemEntity(
        transactionItemId = UUID.randomUUID().toBytes(),
        productId = this.product.productId?.toBytes() ?: UUID.randomUUID().toBytes(),
        productName = product.productName,
        unitPrice = product.price,
        quantity = this.count,
        vatRate = product.vatRate,
        total = this.getCost(),
        transactionId = transactionId
    )
}
