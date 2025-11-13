package com.example.frontendinternship.domain.model


data class ProductWithCount(
    val product: ProductModel = ProductModel(),
    var count: Double = 1.0,
) {
}

fun ProductWithCount.getCost(): Double {
    return product.price * count
}

fun ProductWithCount.getTax(): Double {
    return product.price * (product.vatRate / 100.0) * count
}
