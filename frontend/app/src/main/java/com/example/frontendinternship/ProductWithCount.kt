package com.example.frontendinternship

data class ProductWithCount(
    val product: Product,
    var count: Int
)
fun ProductWithCount.getCost(): Float {
    val price = product.price?.toFloatOrNull() ?: 0f
    return price * count
}
