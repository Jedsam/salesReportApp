package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.Product


interface ProductRepository {
    fun loadProductsByVat(vat: Int) : List<Product>
}