package com.example.frontendinternship.domain.model


data class ProductWithCount(
    val product: ProductModel = ProductModel(),
    var count: Int = 1,
) {
}

fun ProductWithCount.getCost(): Double {
    return product.price * count
}
