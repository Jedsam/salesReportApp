package com.example.frontendinternship.domain.model

import com.example.frontendinternship.data.model.ProductEntity


data class ProductWithCount(
    val product: Product,
    var count: Int,
)
fun ProductWithCount.getCost(): Float {
    val price = product.price.toFloatOrNull() ?: 0f
    return price * count
}
