package com.example.frontendinternship

import android.util.Half.toFloat

data class ProductWithCount(
    val product: Product,
    var count: Int
)
fun ProductWithCount.getCost(): Float {
    val price = product.price?.toFloatOrNull() ?: 0f
    return price * count
}
