package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.data.local.entity.Product
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount

interface ProductRepository {
    fun loadProductsByVat(vat: Int): List<Product>
    fun insertReceipt(products: List<ProductWithCount>, payment: PAYMENT_METHOD)
}