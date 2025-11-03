package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.Product


interface ProductRepository {
    fun loadProducts(): List<Product>
}