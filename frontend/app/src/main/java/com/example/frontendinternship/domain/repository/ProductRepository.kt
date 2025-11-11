package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel
import java.util.UUID


interface ProductRepository {
    fun loadProducts(): List<ProductModel>

    fun removeProducts(uuid: UUID)
    fun editProducts(products: List<ProductModel>)
    fun addProducts(products: List<ProductModel>)

}