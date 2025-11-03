package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.productId.toUUID(),
        productName = this.name,
        price = this.price,
        vatRate = this.vatRate,
        created = this.created,
    )
}