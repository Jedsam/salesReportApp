package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        this.id,
        this.productName ?: "Unknown Name",
        this.vatRate ?: 0,
        this.price?.toFloatOrNull() ?: 0f
    )
}