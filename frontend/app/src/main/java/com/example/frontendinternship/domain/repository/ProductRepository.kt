package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel
import java.util.UUID


interface ProductRepository {
    suspend fun loadProducts(): List<ProductModel>

    suspend fun removeProducts(uuid: UUID)
    suspend fun editProducts(products: List<ProductModel>)
    suspend fun addProducts(products: List<ProductModel>)

}