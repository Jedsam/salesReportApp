package com.example.frontendinternship.domain.model

import com.example.frontendinternship.data.model.ProductEntity


data class ProductWithCount(
    val product: Product = Product(),
    var count: Int = 1,
) {
}

fun ProductWithCount.getCost(): Double {
    return product.price * count
}
