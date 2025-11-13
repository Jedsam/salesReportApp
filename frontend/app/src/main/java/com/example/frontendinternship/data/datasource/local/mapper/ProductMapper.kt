package com.example.frontendinternship.data.datasource.local.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID

fun ProductEntity.toDomain(): ProductModel {
    return ProductModel(
        productId = this.productId.toUUID(),
        productName = this.name,
        price = this.price,
        vatRate = this.vatRate,
        createdAt = this.created,
    )
}

fun ProductModel.toData(isDeleted: Boolean): ProductEntity {
    return ProductEntity(
        productId = this.productId?.toBytes() ?: ByteArray(0),
        name = this.productName,
        price = this.price,
        vatRate = this.vatRate,
        created = this.createdAt,
        isDeleted = isDeleted
    )
}
