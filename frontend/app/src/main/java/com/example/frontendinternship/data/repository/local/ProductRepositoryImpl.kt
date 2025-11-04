package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.model.ProductModel
import jakarta.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun loadProducts(): List<ProductModel> {
        val productEntities: List<ProductEntity> = productDao.getAll()
        return productEntities.map { productEntity: ProductEntity ->
            productEntity.toDomain()
        }
    }
}

