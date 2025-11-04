package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.utils.toUUID

fun ProductEntity.toDomain(): ProductModel {
    return ProductModel(
        id = this.productId.toUUID(),
        productName = this.name,
        price = this.price,
        vatRate = this.vatRate,
        created = this.created,
    )
}