package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel


interface ProductRepository {
    fun loadProducts(): List<ProductModel>
}